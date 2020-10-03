package mashup.backend.tich.category.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
