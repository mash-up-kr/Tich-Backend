package mashup.backend.tich.admin.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.tich.exception.ResultDoseNotExistException;
import mashup.backend.tich.item.domain.Category;
import mashup.backend.tich.item.domain.CategoryRepository;
import mashup.backend.tich.item.dto.CategoryResponseDto;
import mashup.backend.tich.item.dto.CategorySaveRequestDto;
import mashup.backend.tich.item.dto.CategoryUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminCategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryResponseDto> showCategories() {
        List<Category> categories = categoryRepository.findAll();

        return CategoryResponseDto.listOf(categories);
    }

    @Transactional(readOnly = true)
    public CategoryResponseDto showCategory(Long categoryId) {
        Category category = findCategoryById(categoryId);

        return CategoryResponseDto.of(category);
    }

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
        Category category = findCategoryById(requestDto.getId());
        category = category.update(requestDto.getName(), requestDto.getAverageCycle());

        return CategoryResponseDto.of(category);
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = findCategoryById(categoryId);

        categoryRepository.delete(category);
    }

    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(ResultDoseNotExistException::new);
    }
}
