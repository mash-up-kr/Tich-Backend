package mashup.backend.tich.category.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.tich.category.domain.Category;
import mashup.backend.tich.category.domain.CategoryRepository;
import mashup.backend.tich.category.dto.CategoryResponseDto;
import mashup.backend.tich.exception.CategoryDoseNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Category findCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName)
                .orElseThrow(CategoryDoseNotExistException::new);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseDto> showCategories() {
        List<Category> categories = categoryRepository.findAll();

        return CategoryResponseDto.listOf(categories);
    }

    @Transactional(readOnly = true)
    public CategoryResponseDto showCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(CategoryDoseNotExistException::new);

        return CategoryResponseDto.of(category);
    }
}
