package mashup.backend.tich.item.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.tich.exception.ItemDoseNotExistException;
import mashup.backend.tich.exception.NoAccessException;
import mashup.backend.tich.item.domain.*;
import mashup.backend.tich.item.dto.ItemResponseDto;
import mashup.backend.tich.item.dto.ItemSaveRequestDto;
import mashup.backend.tich.item.dto.ItemUpdateRequestDto;
import mashup.backend.tich.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private final CategoryService categoryService;
    private final HistoryService historyService;
    private final ItemCycleService itemCycleService;

    @Transactional(readOnly = true)
    public List<ItemResponseDto> findItems(User user) {
        List<Item> items = itemRepository.findAllByUserId(user.getId());

        return ItemResponseDto.listOf(items);
    }

    @Transactional(readOnly = true)
    public ItemResponseDto findItem(User user, Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemDoseNotExistException(itemId));

        if (!item.isOwner(user)) throw new NoAccessException();

        return ItemResponseDto.of(item);
    }

    @Transactional
    public ItemResponseDto saveItem(ItemSaveRequestDto requestDto, User user) {
        Category category = categoryService.findCategory(requestDto.getCategory());

        LocalDate startDate = itemCycleService.parseDate(requestDto.getStartDate());
        LocalDate scheduledDate = itemCycleService.calculateScheduledDate(startDate, requestDto.getCycle());

        Item item = Item.builder()
                .user(user)
                .category(category)
                .title(requestDto.getTitle())
                .startDate(startDate)
                .latestDate(startDate)
                .scheduledDate(scheduledDate)
                .cycle(requestDto.getCycle())
                .build();
        item = itemRepository.save(item);

        List<History> histories = historyService.createHistory(user, item, startDate);
        item.setHistory(histories);

        return ItemResponseDto.of(item);
    }

    @Transactional
    public ItemResponseDto updateItem(ItemUpdateRequestDto requestDto, User user) {
        Item item = findItemById(requestDto.getItemId());

        if (!item.isOwner(user)) throw new NoAccessException();

        Category category = item.getCategory();
        if (!item.isSameCategory(requestDto.getCategory()))
            category = categoryService.findCategory(requestDto.getCategory());

        LocalDate startDate = LocalDate.parse(requestDto.getStartDate(), DateTimeFormatter.ISO_DATE);
        LocalDate latestDate = item.getLatestDate();
        LocalDate scheduledDate;

        if (item.isSameStartDate(latestDate)) { /* 교체를 한 적이 없는 경우(최근 교체일==시작일) */
            latestDate = startDate;
        }
        scheduledDate = itemCycleService.calculateScheduledDate(latestDate, requestDto.getCycle());

        item = item.update(category, requestDto.getTitle(), startDate, latestDate, scheduledDate, requestDto.getCycle());

        List<History> histories = historyService.updateHistory(user.getId(), item.getId(), startDate, latestDate);
        item.setHistory(histories);

        return ItemResponseDto.of(item);
    }

    @Transactional
    public void deleteItem(User user, Long itemId) {
        Item item = findItemById(itemId);

        if (!item.isOwner(user)) throw new NoAccessException();

        historyService.deleteHistory(user.getId(), itemId);

        itemRepository.delete(item);
    }

    private Item findItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemDoseNotExistException(id));
    }
}