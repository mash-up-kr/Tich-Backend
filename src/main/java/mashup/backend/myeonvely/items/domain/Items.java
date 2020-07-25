package mashup.backend.myeonvely.items.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.backend.myeonvely.common.domain.BaseTimeEntity;
import mashup.backend.myeonvely.users.domain.Users;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Items extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private String name;

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
    public Items(Users user, Category category, String name, LocalDate startDate, LocalDate latestDate, LocalDate scheduledDate, Integer cycle) {
        this.user = user;
        this.category = category;
        this.name = name;
        this.startDate = startDate;
        this.latestDate = latestDate;
        this.scheduledDate = scheduledDate;
        this.cycle = cycle;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }
}
