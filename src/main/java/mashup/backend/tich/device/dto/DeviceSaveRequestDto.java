package mashup.backend.tich.device.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeviceSaveRequestDto {

    private String token;

    @Builder
    public DeviceSaveRequestDto(String token) {
        this.token = token;
    }
}
