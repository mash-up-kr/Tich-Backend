package mashup.backend.myeonvely.users.domain;

import lombok.*;
import mashup.backend.myeonvely.common.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@ToString(exclude = "user")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Devices extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @Builder
    public Devices(String token, Users user){
        this.token = token;
        this.user = user;
    }
}
