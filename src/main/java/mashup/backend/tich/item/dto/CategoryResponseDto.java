package mashup.backend.tich.item.dto;

import lombok.Builder;
import lombok.Getter;
import mashup.backend.tich.item.domain.Category;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoryResponseDto {

    private Long id;
    private String name;
    private Integer averageCycle;

    @Builder
    public CategoryResponseDto(Long id, String name, Integer averageCycle) {
        this.id = id;
        this.name = name;
        this.averageCycle = averageCycle;
    }

    public static CategoryResponseDto of(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .averageCycle(category.getAverageCycle())
                .build();
    }

    public static List<CategoryResponseDto> listOf(List<Category> categories) {
        return categories.stream()
                .map(CategoryResponseDto::of)
                .collect(Collectors.toList());
    }
}
