package cl.edubio360.notification;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class NotificationConsumer {
    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);
    private final CopyOnWriteArrayList<NotificationRecord> processed = new CopyOnWriteArrayList<>();
    private final ObjectMapper objectMapper;

    public NotificationConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void consume(String message) throws Exception {
        JsonNode payload = objectMapper.readTree(message);
        if (!"orientacion.confirmada".equals(payload.path("event").asText())) {
            throw new IllegalArgumentException("Evento no soportado");
        }

        NotificationRecord record = new NotificationRecord(OffsetDateTime.now().toString(), message);
        processed.add(0, record);
        if (processed.size() > 50) {
            processed.remove(processed.size() - 1);
        }
        log.info("Confirmación de orientación recibida: {}", message);
    }

    public List<NotificationRecord> last() {
        return List.copyOf(processed);
    }

    public record NotificationRecord(String receivedAt, String payload) {}
}
