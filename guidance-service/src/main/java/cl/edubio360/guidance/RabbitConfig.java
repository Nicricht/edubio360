package cl.edubio360.guidance;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "edubio.orientation";
    public static final String QUEUE = "orientation.confirmed.notification";
    public static final String ROUTING_KEY = "orientation.confirmed";
    public static final String DLX = "edubio.orientation.dlx";
    public static final String DLQ = "orientation.confirmed.notification.dlq";
    public static final String DLQ_ROUTING_KEY = "orientation.confirmed.dlq";

    @Bean
    DirectExchange orientationExchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(DLX, true, false);
    }

    @Bean
    Queue notificationQueue() {
        return QueueBuilder.durable(QUEUE)
                .deadLetterExchange(DLX)
                .deadLetterRoutingKey(DLQ_ROUTING_KEY)
                .build();
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(DLQ).build();
    }

    @Bean
    Binding orientationBinding() {
        return BindingBuilder.bind(notificationQueue()).to(orientationExchange()).with(ROUTING_KEY);
    }

    @Bean
    Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DLQ_ROUTING_KEY);
    }
}
