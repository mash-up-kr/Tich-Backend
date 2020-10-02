package mashup.backend.tich.user.domain;

import lombok.*;
import mashup.backend.tich.common.domain.BaseTimeEntity;
import mashup.backend.tich.device.domain.Device;

import javax.persistence.*;
import java.util.List;

@Getter
@ToString(exclude = "devices")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Device> devices;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    @Builder
    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
