package mashup.backend.tich.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpResponseDto {

    private Long id;
    private String token;
    private String name;

    @Builder
    public SignUpResponseDto(Long id, String token, String name){
        this.id = id;
        this.token = token;
        this.name = name;
    }
}