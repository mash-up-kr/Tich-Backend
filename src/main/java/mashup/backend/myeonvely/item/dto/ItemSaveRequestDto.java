package mashup.backend.myeonvely.item.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemSaveRequestDto {
    private String category;
    private String title;
    private String startDate;
    private Integer cycle;

    @Builder
    public ItemSaveRequestDto(String category, String title, String startDate, Integer cycle) {
        this.category = category;
        this.title = title;
        this.startDate = startDate;
        this.cycle = cycle;
    }
}
