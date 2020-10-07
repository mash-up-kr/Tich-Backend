package mashup.backend.tich.push.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import mashup.backend.tich.push.service.PushService;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
public class PushController {

    private final PushService pushService;

    public void sendItemPushAlarm(LocalDate now) throws FirebaseMessagingException {
        pushService.sendItemPushAlarm(now);
    }
}
