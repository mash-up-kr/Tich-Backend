package mashup.backend.tich.product.dto;

import lombok.Builder;
import lombok.Getter;
import mashup.backend.tich.product.domain.Product;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductResponseDto {

    private Long id;
    private Long categoryId;
    private String name;
    private String description;
    private Integer cycle;
    private String imageUrl;
    private Integer price;

    @Builder
    public ProductResponseDto(Long id, Long categoryId, String name, String description, Integer cycle, String imageUrl, Integer price) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.cycle = cycle;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public static ProductResponseDto of(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .categoryId(product.getCategory().getId())
                .name(product.getName())
                .description(product.getDescription())
                .cycle(product.getCycle())
                .imageUrl(product.getImageUrl())
                .price(product.getPrice())
                .build();
    }

    public static List<ProductResponseDto> listOf(List<Product> products) {
        return products.stream()
                .map(ProductResponseDto::of)
                .collect(Collectors.toList());
    }
}
