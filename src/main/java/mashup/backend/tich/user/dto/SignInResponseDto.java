package mashup.backend.tich.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInResponseDto {

    private Long id;
    private String token;
    private String name;

    @Builder
    public SignInResponseDto(Long id, String token, String name) {
        this.id = id;
        this.token = token;
        this.name = name;
    }
}