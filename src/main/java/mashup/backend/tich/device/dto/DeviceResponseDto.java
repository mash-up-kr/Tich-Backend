package mashup.backend.tich.device.dto;

import lombok.Builder;
import lombok.Getter;
import mashup.backend.tich.device.domain.Device;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DeviceResponseDto {

    private Long id;
    private String token;

    @Builder
    public DeviceResponseDto(Long id, String token) {
        this.id = id;
        this.token = token;
    }

    public static DeviceResponseDto of(Device device) {
        return DeviceResponseDto.builder()
                .id(device.getId())
                .token(device.getToken())
                .build();
    }

    public static List<DeviceResponseDto> listOf(List<Device> devices) {
        return devices.stream()
                .map(DeviceResponseDto::of)
                .collect(Collectors.toList());
    }
}
