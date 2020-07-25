package mashup.backend.tich.category.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.tich.category.domain.Category;
import mashup.backend.tich.category.domain.CategoryRepository;
import mashup.backend.tich.category.dto.CategoryResponseDto;
import mashup.backend.tich.category.dto.CategorySaveRequestDto;
import mashup.backend.tich.category.dto.CategoryUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminCategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @Transactional
    public CategoryResponseDto saveCategory(CategorySaveRequestDto requestDto) {
        Category category = Category.builder()
                .name(requestDto.getName())
                .averageCycle(requestDto.getAverageCycle())
                .build();
        category = categoryRepository.save(category);

        return CategoryResponseDto.of(category);
    }

    @Transactional
    public CategoryResponseDto updateCategory(CategoryUpdateRequestDto requestDto) {
        Category category = categoryService.findCategoryById(requestDto.getId());
        category = category.update(requestDto.getName(), requestDto.getAverageCycle());

        return CategoryResponseDto.of(category);
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = categoryService.findCategoryById(categoryId);

        categoryRepository.delete(category);
    }
}
