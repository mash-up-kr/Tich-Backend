package mashup.backend.myeonvely.items.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Items item;

    @Column(nullable = false)
    private LocalDate replacement_date;

    @Builder
    public History(Items item, LocalDate replacement_date) {
        this.item = item;
        this.replacement_date = replacement_date;
    }
}
