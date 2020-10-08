package mashup.backend.tich.push.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import mashup.backend.tich.item.domain.Item;
import mashup.backend.tich.item.domain.ItemRepository;
import mashup.backend.tich.item.service.ItemCycleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PushService {

    private final NotificationService notificationService;
    private final ItemRepository itemRepository;
    private final ItemCycleService itemCycleService;

    private final int MAX_SIZE = 1; // 500

    private String TITLE = "TICH";
    private String TEXT = "교체일 입니다!";

    public void sendItemPushAlarm(LocalDate now) throws FirebaseMessagingException {
        List<Item> itemList = itemRepository.findAll();

        List<String> tokens = new ArrayList<String>();

        for (Item item : itemList) {
            if (item.getScheduledDate().equals(now)) {
                // 사용자 찾아서 token 값으로 push 알림 보내기
                tokens.addAll(itemCycleService.getTokens(item.getUser()));
                if(tokens.size() == MAX_SIZE){ // 의심
                    notificationService.send(tokens, TITLE, TEXT);
                    tokens.clear();
                }
//                tokens.addAll(itemCycleService.getTokens(item.getUser()));
            }
        }
//        if(tokens.size() > 0)
//            notificationService.send(tokens, TITLE, TEXT);
    }
}
