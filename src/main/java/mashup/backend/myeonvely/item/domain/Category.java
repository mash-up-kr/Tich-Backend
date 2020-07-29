package mashup.backend.myeonvely.item.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.backend.myeonvely.common.domain.BaseTimeEntity;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer average_cycle;

    @OneToMany(mappedBy = "category")
    private List<Item> items;

    @Builder
    public Category(String name, Integer average_cycle) {
        this.name = name;
        this.average_cycle = average_cycle;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
