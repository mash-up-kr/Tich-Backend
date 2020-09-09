package mashup.backend.tich.product.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.tich.exception.ProductDoseNotExistException;
import mashup.backend.tich.product.domain.Product;
import mashup.backend.tich.product.domain.ProductRepository;
import mashup.backend.tich.product.dto.ProductResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductResponseDto> showProducts() {
        List<Product> products = productRepository.findAll();

        return ProductResponseDto.listOf(products);
    }

    @Transactional(readOnly = true)
    public ProductResponseDto showProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductDoseNotExistException::new);

        return ProductResponseDto.of(product);
    }
}
