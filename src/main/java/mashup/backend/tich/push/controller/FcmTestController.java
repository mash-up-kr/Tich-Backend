package mashup.backend.tich.push.controller;

import lombok.RequiredArgsConstructor;
import mashup.backend.tich.push.service.FcmTestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FcmTestController {

    private final FcmTestService fcmTestService;

    @PostMapping("/push")
    public ResponseEntity<Void> sendPush(@RequestHeader("TICH-TOKEN") String token) {

        fcmTestService.send(token);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
