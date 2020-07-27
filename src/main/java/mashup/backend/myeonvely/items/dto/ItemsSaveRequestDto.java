package mashup.backend.myeonvely.items.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemsSaveRequestDto {
    private String category;
    private String title;
    private String startDate;
    private Integer cycle;

    @Builder
    public ItemsSaveRequestDto(String category, String title, String startDate, Integer cycle) {
        this.category = category;
        this.title = title;
        this.startDate = startDate;
        this.cycle = cycle;
    }
}
