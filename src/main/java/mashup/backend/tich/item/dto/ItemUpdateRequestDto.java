package mashup.backend.tich.item.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemUpdateRequestDto {

    private Long itemId;
    private String category;
    private String title;
    private String startDate;
    private Integer cycle;

    @Builder
    public ItemUpdateRequestDto(Long itemId, String category, String title, String startDate, Integer cycle) {
        this.itemId = itemId;
        this.category = category;
        this.title = title;
        this.startDate = startDate;
        this.cycle = cycle;
    }
}
