package mashup.backend.tich.push.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

interface NotificationService {

    @Async("pushSenderThreadPool")
    public void send(List<String> tokens, String title, String body) throws FirebaseMessagingException;
}
