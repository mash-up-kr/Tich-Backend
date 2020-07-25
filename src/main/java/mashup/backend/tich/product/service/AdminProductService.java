package mashup.backend.tich.product.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.tich.category.domain.Category;
import mashup.backend.tich.category.service.CategoryService;
import mashup.backend.tich.exception.ProductDoseNotExistException;
import mashup.backend.tich.product.domain.Product;
import mashup.backend.tich.product.domain.ProductRepository;
import mashup.backend.tich.product.dto.ProductResponseDto;
import mashup.backend.tich.product.dto.ProductSaveRequestDto;
import mashup.backend.tich.product.dto.ProductUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminProductService {

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final CategoryService categoryService;

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

    @Transactional
    public ProductResponseDto saveProduct(ProductSaveRequestDto requestDto) {
        Category category = categoryService.findCategoryById(requestDto.getCategoryId());

        Product product = Product.builder()
                .category(category)
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .cycle(requestDto.getCycle())
                .imageUrl(requestDto.getImageUrl())
                .price(requestDto.getPrice())
                .build();
        product = productRepository.save(product);

        return ProductResponseDto.of(product);
    }

    @Transactional
    public ProductResponseDto updateProduct(ProductUpdateRequestDto requestDto) {
        Category category = categoryService.findCategoryById(requestDto.getCategoryId());

        Product product = productService.findProductById(requestDto.getId());
        product = product.update(requestDto.getName(), requestDto.getDescription(), requestDto.getCycle(), requestDto.getImageUrl(), requestDto.getPrice(), category);

        return ProductResponseDto.of(product);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product product = productService.findProductById(productId);

        productRepository.delete(product);
    }
}
