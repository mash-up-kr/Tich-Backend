package mashup.backend.myeonvely.item.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.myeonvely.exception.CategoryDoseNotExistException;
import mashup.backend.myeonvely.item.domain.Category;
import mashup.backend.myeonvely.item.domain.CategoryRepository;
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