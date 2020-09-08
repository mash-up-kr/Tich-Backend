package mashup.backend.tich.category.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategorySaveRequestDto {

    private String name;
    private Integer averageCycle;

    @Builder
    public CategorySaveRequestDto(String name, Integer averageCycle) {
        this.name = name;
        this.averageCycle = averageCycle;
    }
}
