package mashup.backend.tich.product.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.tich.exception.ProductDoseNotExistException;
import mashup.backend.tich.product.domain.Product;
import mashup.backend.tich.product.domain.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(ProductDoseNotExistException::new);
    }
}
