package mashup.backend.tich.item.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.tich.exception.CategoryDoseNotExistException;
import mashup.backend.tich.item.domain.Category;
import mashup.backend.tich.item.domain.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Category findCategory(String categoryName) {
        return categoryRepository.findByName(categoryName)
                .orElseThrow(CategoryDoseNotExistException::new);
    }
}