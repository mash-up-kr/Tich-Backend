package mashup.backend.tich.push.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import mashup.backend.tich.device.service.DeviceService;
import mashup.backend.tich.item.domain.Item;
import mashup.backend.tich.item.service.ItemCycleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PushService {

    private final NotificationService notificationService;
    private final ItemCycleService itemCycleService;
    private final DeviceService deviceService;

    private final int MAX_SIZE = 1; // 500

    private String TITLE = "TICH";
    private String TEXT = "교체일 입니다!";

    @Transactional
    public void sendItemPushAlarm(LocalDate now) throws FirebaseMessagingException {
        List<Item> itemList = itemCycleService.findAllByScheduledDate(now);

        List<String> tokens = new ArrayList<>();

        for (Item item : itemList) {
            tokens.addAll(itemCycleService.getTokens(item.getUser()));
            if (tokens.size() == MAX_SIZE) {
                notificationService.send(tokens, TITLE, TEXT);
                tokens = new ArrayList<>();
            }
        }
//        if(tokens.size() > 0)
//            notificationService.send(tokens, TITLE, TEXT);
    }
}
