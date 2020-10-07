package mashup.backend.tich.push.service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mashup.backend.tich.exception.CanNotSendMessageException;
import mashup.backend.tich.item.service.ItemCycleService;
import mashup.backend.tich.user.domain.User;
import mashup.backend.tich.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FcmTestService {

    private final FirebaseApp firebaseApp;
    private final UserService userService;
    private final ItemCycleService itemCycleService;

    public void send(String token) {
       User user = userService.findUserByToken(token);

        Message message = Message.builder()
                .setNotification(new Notification("test title", "test body"))
                .putData("title", "test title")
                .putData("body", "test body")
                .putData("type", "test type")
                .setToken(itemCycleService.getTokens(user).get(0))
                .build();
        try {
            FirebaseMessaging.getInstance(firebaseApp).send(message);
        } catch (FirebaseMessagingException e) {
            log.error("Fcm Send Error - userId" + user.getId());
            log.error("Fcm Send Error - token" + token);
            throw new CanNotSendMessageException();
        }
    }
}
