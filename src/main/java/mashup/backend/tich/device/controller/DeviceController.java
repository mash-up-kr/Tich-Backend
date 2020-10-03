package mashup.backend.tich.device.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.backend.tich.device.dto.DeviceResponseDto;
import mashup.backend.tich.device.dto.DeviceSaveRequestDto;
import mashup.backend.tich.device.service.DeviceService;
import mashup.backend.tich.user.domain.User;
import mashup.backend.tich.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;
    private final UserService userService;

    @ApiOperation("장치 목록 조회")
    @GetMapping
    public ResponseEntity<List<DeviceResponseDto>> findDevices(@RequestHeader("TICH-TOKEN") String token) {
        User user = userService.findUserByToken(token);

        List<DeviceResponseDto> deviceResponseDto = deviceService.findDevices(user);

        return ResponseEntity.status(HttpStatus.OK).body(deviceResponseDto);
    }

    @ApiOperation("장치 등록")
    @PostMapping
    public ResponseEntity<DeviceResponseDto> saveDevice(@RequestHeader("TICH-TOKEN") String token,
                                                        @RequestBody DeviceSaveRequestDto requestDto) {
        User user = userService.findUserByToken(token);

        DeviceResponseDto deviceResponseDto = deviceService.saveDevice(requestDto, user);

        return ResponseEntity.status(HttpStatus.CREATED).body(deviceResponseDto);
    }
}
