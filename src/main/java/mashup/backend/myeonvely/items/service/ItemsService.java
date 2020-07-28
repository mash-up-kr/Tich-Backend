package mashup.backend.myeonvely.items.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.myeonvely.items.domain.*;
import mashup.backend.myeonvely.items.dto.ItemsResponseDto;
import mashup.backend.myeonvely.items.dto.ItemsSaveRequestDto;
import mashup.backend.myeonvely.users.domain.Users;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemsService {

    private final ItemsRepository itemsRepository;
    private final CategoryRepository categoryRepository;
    private final HistoryRepository historyRepository;

    @Transactional(readOnly = true)
    public List<ItemsResponseDto> findItems(Users user) {
        List<ItemsResponseDto> itemsResponseDtos = new ArrayList<>();
        List<Items> items = itemsRepository.findAllByUserId(user.getId());

        for(Items item : items) {
            itemsResponseDtos.add(ItemsResponseDto.builder()
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
        return itemsResponseDtos;
    }

    @Transactional(readOnly = true)
    public ItemsResponseDto findItem(Users user, Long itemId) {
        Items item = itemsRepository.findById(itemId)
                .orElseThrow(() -> new NoResultException());

        if(!item.getUser().getId().equals(user.getId()))
            throw new SecurityException("This user does not have access.");

        return ItemsResponseDto.builder()
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
    public ItemsResponseDto saveItem(ItemsSaveRequestDto requestDto, Users user) {
        Category category = categoryRepository.findByName(requestDto.getCategory())
                .orElseThrow(() -> new NoResultException());

        LocalDate startDate = LocalDate.parse(requestDto.getStartDate(), DateTimeFormatter.ISO_DATE);

        Items item = itemsRepository.save(Items.builder()
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

        return ItemsResponseDto.builder()
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

    private LocalDate calculateScheduledDate(LocalDate latestDate, Integer cycle) {
        return latestDate.plusDays(cycle);
    }
}