package mashup.backend.tich.item.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
