package mashup.backend.myeonvely.item.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.backend.myeonvely.common.domain.BaseTimeEntity;
import mashup.backend.myeonvely.user.domain.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate latestDate;

    @Column(nullable = false)
    private LocalDate scheduledDate;

    @Column(nullable = false)
    private Integer cycle;

    @OneToMany(mappedBy = "item")
    private List<History> history;

    @Builder
    public Item(User user, Category category, String title, LocalDate startDate, LocalDate latestDate, LocalDate scheduledDate, Integer cycle) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.startDate = startDate;
        this.latestDate = latestDate;
        this.scheduledDate = scheduledDate;
        this.cycle = cycle;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public Item update(Category category, String title, LocalDate startDate, LocalDate latestDate, LocalDate scheduledDate, Integer cycle) {
        this.category = category;
        this.title = title;
        this.startDate = startDate;
        this.latestDate = latestDate;
        this.scheduledDate = scheduledDate;
        this.cycle = cycle;
        return this;
    }
}
