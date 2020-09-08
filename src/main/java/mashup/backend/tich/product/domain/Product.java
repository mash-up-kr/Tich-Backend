package mashup.backend.tich.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.backend.tich.category.domain.Category;
import mashup.backend.tich.common.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer cycle;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public Product(String name, String description, Integer cycle, String imageUrl, Integer price, Category category) {
        this.name = name;
        this.description = description;
        this.cycle = cycle;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category;
    }
}
