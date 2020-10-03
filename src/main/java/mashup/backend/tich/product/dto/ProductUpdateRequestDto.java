package mashup.backend.tich.product.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductUpdateRequestDto {

    private Long id;
    private Long categoryId;
    private String name;
    private String description;
    private Integer cycle;
    private String imageUrl;
    private Integer price;

    @Builder
    public ProductUpdateRequestDto(Long id, Long categoryId, String name, String description, Integer cycle, String imageUrl, Integer price) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.cycle = cycle;
        this.imageUrl = imageUrl;
        this.price = price;
    }
}
