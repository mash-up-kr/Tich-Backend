package mashup.backend.tich.category.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryUpdateRequestDto {

    private Long id;
    private String name;
    private Integer averageCycle;

    @Builder
    public CategoryUpdateRequestDto(Long id, String name, Integer averageCycle) {
        this.id = id;
        this.name = name;
        this.averageCycle = averageCycle;
    }
}
