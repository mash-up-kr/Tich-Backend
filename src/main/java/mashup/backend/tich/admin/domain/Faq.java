package mashup.backend.tich.admin.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.backend.tich.common.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "faq")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Faq extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String question;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String answer;

    @Builder
    public Faq(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Faq update(String question, String answer) {
        this.question = question;
        this.answer = answer;
        return this;
    }
}
