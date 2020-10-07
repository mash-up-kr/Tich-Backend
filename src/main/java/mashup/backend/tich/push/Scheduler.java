package mashup.backend.tich.push;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import mashup.backend.tich.push.controller.PushController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class Scheduler {

    private final PushController pushController;

//    @Scheduled(cron = "0 0 8 * * *") // 매일 8시마다
    @Scheduled(fixedDelay = 3000) // 30초 마다 호출
    public void itemJobSch() throws FirebaseMessagingException {
        pushController.sendItemPushAlarm(LocalDate.now());
    }
}
