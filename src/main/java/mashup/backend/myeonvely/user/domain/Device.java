package mashup.backend.myeonvely.user.domain;

import lombok.*;
import mashup.backend.myeonvely.common.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@ToString(exclude = "user")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Device extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Device(String token, User user){
        this.token = token;
        this.user = user;
    }
}
