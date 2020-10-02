package mashup.backend.tich.device.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.tich.device.domain.Device;
import mashup.backend.tich.device.domain.DeviceRepository;
import mashup.backend.tich.device.dto.DeviceResponseDto;
import mashup.backend.tich.device.dto.DeviceSaveRequestDto;
import mashup.backend.tich.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Transactional(readOnly = true)
    public List<DeviceResponseDto> findDevices(User user) {
        List<Device> devices = deviceRepository.findAllByUserId(user.getId());

        return DeviceResponseDto.listOf(devices);
    }

    @Transactional
    public DeviceResponseDto saveDevice(DeviceSaveRequestDto requestDto, User user) {
        Device device = deviceRepository.findByTokenAndUserId(requestDto.getToken(), user.getId());

        if (!isDuplicateToken(device)) {
            device = Device.builder()
                    .token(requestDto.getToken())
                    .user(user)
                    .build();
            device = deviceRepository.save(device);
        }

        return DeviceResponseDto.of(device);
    }

    private boolean isDuplicateToken(Device device) {
        return device != null;
    }
}
