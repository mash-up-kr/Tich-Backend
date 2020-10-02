package mashup.backend.tich.device.domain;

import lombok.*;
import mashup.backend.tich.common.domain.BaseTimeEntity;
import mashup.backend.tich.user.domain.User;

import javax.persistence.*;

@Getter
@Entity
@ToString(exclude = "user")
@Table(name = "devices")
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
    public Device(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
