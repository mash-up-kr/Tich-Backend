package mashup.backend.myeonvely.items.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ItemsResponseDto {
    private Long id;
    private Long userId;
    private Long categoryId;
    private String title;
    private LocalDate startDate;
    private LocalDate latestDate;
    private LocalDate scheduledDate;
    private Integer cycle;

    @Builder
    public ItemsResponseDto(Long id, Long userId, Long categoryId, String title, LocalDate startDate, LocalDate latestDate, LocalDate scheduledDate, Integer cycle) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.startDate = startDate;
        this.latestDate = latestDate;
        this.scheduledDate = scheduledDate;
        this.cycle = cycle;
    }
}
