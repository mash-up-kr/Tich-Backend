package mashup.backend.tich.category.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.backend.tich.common.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "categories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer averageCycle;

    @Builder
    public Category(String name, Integer averageCycle) {
        this.name = name;
        this.averageCycle = averageCycle;
    }

    public Category update(String name, Integer averageCycle) {
        this.name = name;
        this.averageCycle = averageCycle;

        return this;
    }
}
