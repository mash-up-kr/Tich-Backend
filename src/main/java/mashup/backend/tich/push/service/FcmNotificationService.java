package mashup.backend.tich.push.service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FcmNotificationService implements NotificationService {

    private Logger logger = LoggerFactory.getLogger(FcmNotificationService.class);
    private final FirebaseApp firebaseApp;

    @Override
    public void send(List<String> tokens, String title, String body) throws FirebaseMessagingException {
        Notification notification = new Notification(title, body);
        MulticastMessage message = MulticastMessage.builder()
                .setNotification(notification)
                .putData("title", title)
                .putData("body", body)
                .addAllTokens(tokens)
                .build();

        BatchResponse response = FirebaseMessaging.getInstance(firebaseApp).sendMulticast(message);
        if (response.getFailureCount() > 0) {
            List<SendResponse> responses = response.getResponses();
            List<String> failedTokens = new ArrayList<String>();

            for (int i = 0; i < responses.size(); i++) {
                if (!responses.get(i).isSuccessful())
                    failedTokens.add(tokens.get(i));
            }
            System.out.println("Fail FCM");
            logger.error("FCM 실패!");
        }
    }
}
