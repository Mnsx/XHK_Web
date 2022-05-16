package top.mnsx.xhk.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE = "E";
    public static final String QUEUE_A = "QA";
    public static final String QUEUE_B = "QB";

    @Bean("exchange")
    public DirectExchange exchange() {
        return new ExchangeBuilder(EXCHANGE, "direct").build();
    }

    @Bean("queueA")
    public Queue queueA() {
        return QueueBuilder.durable(QUEUE_A).build();
    }

    @Bean("queueB")
    public Queue queueB() {
        return QueueBuilder.durable(QUEUE_B).build();
    }

    @Bean
    public Binding queueBBinding(@Qualifier("queueB") Queue queueB, @Qualifier("exchange") DirectExchange exchange) {
        return BindingBuilder.bind(queueB).to(exchange).with("EB");
    }

    @Bean
    public Binding queueBBindingX(@Qualifier("queueA") Queue queueA, @Qualifier("exchange") DirectExchange exchange) {
        return BindingBuilder.bind(queueA).to(exchange).with("EA");
    }
}
