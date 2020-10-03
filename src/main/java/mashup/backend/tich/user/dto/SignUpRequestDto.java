package mashup.backend.tich.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.backend.tich.user.domain.Role;
import mashup.backend.tich.user.domain.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {

    private String token; // 구글로그인 sub
    private String name;
    private String email;

    public User toEntity() {
        return User.builder().name(name).email(email).role(Role.USER).build();
    }
}