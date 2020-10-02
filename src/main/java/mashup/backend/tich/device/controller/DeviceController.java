package mashup.backend.tich.device.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.backend.tich.device.dto.DeviceResponseDto;
import mashup.backend.tich.device.dto.DeviceSaveRequestDto;
import mashup.backend.tich.device.service.DeviceService;
import mashup.backend.tich.user.domain.Role;
import mashup.backend.tich.user.domain.User;
import mashup.backend.tich.user.domain.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;
    private final UserRepository userRepository; /* 삭제 예정 */

    @ApiOperation("장치 목록 조회")
    @GetMapping
    public ResponseEntity<List<DeviceResponseDto>> findDevices(@RequestHeader String accessToken) {
        // 임시 코드 : 추후 수정
        User user;
        try {
            user = userRepository.findByEmail("temp")
                    .orElseThrow(() -> new NoResultException());
        } catch (NoResultException e) {
            user = userRepository.save(User.builder()
                    .name("temp")
                    .email("temp")
                    .picture("temp")
                    .role(Role.USER)
                    .build());
        }
        // ToDo : user check (accessToken)

        List<DeviceResponseDto> deviceResponseDto = deviceService.findDevices(user);

        return ResponseEntity.status(HttpStatus.OK).body(deviceResponseDto);
    }

    @ApiOperation("장치 등록")
    @PostMapping
    public ResponseEntity<DeviceResponseDto> saveDevice(@RequestHeader String accessToken,
                                                        @RequestBody DeviceSaveRequestDto requestDto) {
        // 임시 코드 : 추후 수정
        User user;
        try {
            user = userRepository.findByEmail("temp")
                    .orElseThrow(() -> new NoResultException());
        } catch (NoResultException e) {
            user = userRepository.save(User.builder()
                    .name("temp")
                    .email("temp")
                    .picture("temp")
                    .role(Role.USER)
                    .build());
        }
        // ToDo : user check (accessToken)

        DeviceResponseDto deviceResponseDto = deviceService.saveDevice(requestDto, user);

        return ResponseEntity.status(HttpStatus.CREATED).body(deviceResponseDto);
    }
}
