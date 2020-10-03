package mashup.backend.tich.category.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategorySaveRequestDto {

    private String name;
    private Integer averageCycle;

    @Builder
    public CategorySaveRequestDto(String name, Integer averageCycle) {
        this.name = name;
        this.averageCycle = averageCycle;
    }
}
