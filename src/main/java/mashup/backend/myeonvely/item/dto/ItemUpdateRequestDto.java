package mashup.backend.myeonvely.item.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
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
