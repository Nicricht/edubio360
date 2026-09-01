package cl.edubio360.notification;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificationController {
    private final NotificationConsumer consumer;

    public NotificationController(NotificationConsumer consumer) {
        this.consumer = consumer;
    }

    @GetMapping
    public List<NotificationConsumer.NotificationRecord> last() {
        return consumer.last();
    }
}
