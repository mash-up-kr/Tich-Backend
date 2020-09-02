package mashup.backend.myeonvely.item.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.backend.myeonvely.user.domain.User;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(nullable = false)
    private LocalDate replacementDate;

    @Builder
    public History(User user, Item item, LocalDate replacementDate) {
        this.user = user;
        this.item = item;
        this.replacementDate = replacementDate;
    }

    public History updateDate(LocalDate replacementDate) {
        this.replacementDate = replacementDate;
        return this;
    }
}
