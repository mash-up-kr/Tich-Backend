package mashup.backend.myeonvely.item.dto;

import lombok.Builder;
import lombok.Getter;
import mashup.backend.myeonvely.item.domain.Item;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ItemResponseDto {

    private Long id;
    private Long userId;
    private Long categoryId;
    private String title;
    private LocalDate startDate;
    private LocalDate latestDate;
    private LocalDate scheduledDate;
    private Integer cycle;

    @Builder
    public ItemResponseDto(Long id, Long userId, Long categoryId, String title, LocalDate startDate, LocalDate latestDate, LocalDate scheduledDate, Integer cycle) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.startDate = startDate;
        this.latestDate = latestDate;
        this.scheduledDate = scheduledDate;
        this.cycle = cycle;
    }

    public static ItemResponseDto of(Item item) {
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

    public static List<ItemResponseDto> listOf(List<Item> items) {
        return items.stream()
                .map(ItemResponseDto::of)
                .collect(Collectors.toList());
    }
}
