package mashup.backend.myeonvely.item.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.myeonvely.item.domain.*;
import mashup.backend.myeonvely.item.dto.ItemResponseDto;
import mashup.backend.myeonvely.item.dto.ItemSaveRequestDto;
import mashup.backend.myeonvely.item.dto.ItemUpdateRequestDto;
import mashup.backend.myeonvely.item.dto.ItemsResponseDto;
import mashup.backend.myeonvely.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final HistoryRepository historyRepository;

    @Transactional(readOnly = true)
    public ItemsResponseDto findItems(User user) {
        List<ItemResponseDto> itemsResponseDtos = new ArrayList<>();
        List<Item> items = itemRepository.findAllByUserId(user.getId());

        for (Item item : items) {
            itemsResponseDtos.add(ItemResponseDto.builder()
                    .id(item.getId())
                    .userId(item.getUser().getId())
                    .categoryId(item.getCategory().getId())
                    .title(item.getTitle())
                    .startDate(item.getStartDate())
                    .latestDate(item.getLatestDate())
                    .scheduledDate(item.getScheduledDate())
                    .cycle(item.getCycle())
                    .build());
        }
        return ItemsResponseDto.builder()
                .itemResponseDtos(itemsResponseDtos)
                .build();
    }

    @Transactional(readOnly = true)
    public ItemResponseDto findItem(User user, Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NoResultException("There is no result for this item id."));

        if (!item.getUser().getId().equals(user.getId()))
            throw new SecurityException("This user does not have access.");

        return ItemResponseDto.builder()
                .id(item.getId())
                .userId(item.getUser().getId())
                .categoryId(item.getCategory().getId())
                .title(item.getTitle())
                .startDate(item.getStartDate())
                .latestDate(item.getLatestDate())
                .scheduledDate(item.getScheduledDate())
                .cycle(item.getCycle())
                .build();
    }

    @Transactional
    public ItemResponseDto saveItem(ItemSaveRequestDto requestDto, User user) {
        Category category = categoryRepository.findByName(requestDto.getCategory())
                .orElseThrow(() -> new NoResultException("There is no result for this category name."));

        LocalDate startDate = LocalDate.parse(requestDto.getStartDate(), DateTimeFormatter.ISO_DATE);

        Item item = itemRepository.save(Item.builder()
                .user(user)
                .category(category)
                .title(requestDto.getTitle())
                .startDate(startDate)
                .latestDate(startDate)
                .scheduledDate(calculateScheduledDate(startDate, requestDto.getCycle()))
                .cycle(requestDto.getCycle())
                .build());

        List<History> history = new ArrayList<>();
        history.add(historyRepository.save(History.builder()
                .item(item)
                .replacement_date(startDate)
                .build()));

        item.setHistory(history);

        return ItemResponseDto.builder()
                .id(item.getId())
                .userId(item.getUser().getId())
                .categoryId(item.getCategory().getId())
                .title(item.getTitle())
                .startDate(item.getStartDate())
                .latestDate(item.getLatestDate())
                .scheduledDate(item.getScheduledDate())
                .cycle(item.getCycle())
                .build();
    }

    @Transactional
    public ItemResponseDto updateItem(ItemUpdateRequestDto requestDto, User user) {
        Item item = itemRepository.findById(requestDto.getItemId())
                .orElseThrow(() -> new NoResultException("There is no result for this item id."));

        if (!item.getUser().getId().equals(user.getId()))
            throw new SecurityException("This user does not have access.");

        Category category = checkCategory(item.getCategory(), requestDto.getCategory());

        LocalDate startDate = LocalDate.parse(requestDto.getStartDate(), DateTimeFormatter.ISO_DATE);
        LocalDate latestDate = item.getLatestDate();
        LocalDate scheduledDate;

        // 최근 교체일이 시작일과 동일한지 Check
        if (latestDate.equals(item.getStartDate())) { /* 교체를 한 적이 없는 경우 */
            latestDate = startDate;
        }
        scheduledDate = calculateScheduledDate(latestDate, requestDto.getCycle());

        item.update(category, requestDto.getTitle(), startDate, latestDate, scheduledDate, requestDto.getCycle());

        updateHistory(item, startDate);

        return ItemResponseDto.builder()
                .id(item.getId())
                .userId(item.getUser().getId())
                .categoryId(item.getCategory().getId())
                .title(item.getTitle())
                .startDate(item.getStartDate())
                .latestDate(item.getLatestDate())
                .scheduledDate(item.getScheduledDate())
                .cycle(item.getCycle())
                .build();
    }

    @Transactional
    public void deleteItem(User user, Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NoResultException("There is no result for this item id."));

        if (!item.getUser().getId().equals(user.getId()))
            throw new SecurityException("This user does not have access.");

        itemRepository.delete(item);
//        historyRepository.deleteAllByItemId(itemId);
    }

    private LocalDate calculateScheduledDate(LocalDate latestDate, Integer cycle) {
        return latestDate.plusDays(cycle);
    }

    private Category checkCategory(Category category, String compareName) {
        if (!category.getName().equals(compareName)) {
            category = categoryRepository.findByName(compareName)
                    .orElseThrow(() -> new NoResultException("There is no result for this category name."));
        }
        return category;
    }

    private void updateHistory(Item item, LocalDate startDate) {
        List<History> history = new ArrayList<>();
                            // = historyRepository.(item.getId());

        if(history.size() > 0) {
//            history.get(0).update(startDate);
        }
        item.setHistory(history);
    }
}