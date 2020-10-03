package mashup.backend.tich.product.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductSaveRequestDto {

    private Long categoryId;
    private String name;
    private String description;
    private Integer cycle;
    private String imageUrl;
    private Integer price;

    @Builder
    public ProductSaveRequestDto(Long categoryId, String name, String description, Integer cycle, String imageUrl, Integer price) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.cycle = cycle;
        this.imageUrl = imageUrl;
        this.price = price;
    }
}
