package thinhld.ldt.roomservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    // Khai báo các object
    public static final String QUEUE_USER = "queue.user";
    public static final String ROUTING_USER = "routing.user";
    public static final String USER_EXCHANGE = "exchange.user";
    public static final String QUEUE_BED = "queue.bed";
    public static final String ROUTING_BED = "routing.bed";
    public static final String BED_EXCHANGE = "exchange.bed";


    @Bean
    Queue queueA() {
        return new Queue(QUEUE_USER, false);
    }

    @Bean
    Queue queueB() {
        return new Queue(QUEUE_BED, false);
    }

    @Bean
    TopicExchange user_exchange() {
        return new TopicExchange(USER_EXCHANGE);
    }

    @Bean
    TopicExchange bed_exchange() {
        return new TopicExchange(BED_EXCHANGE);
    }

    //      Topic binding
    @Bean
    Binding bindingUser() {
        return BindingBuilder.bind(queueA()).to(user_exchange()).with(ROUTING_USER);
    }

    @Bean
    Binding bindingBed() {
        return BindingBuilder.bind(queueB()).to(bed_exchange()).with(ROUTING_BED);
    }

    // Dùng chung các hàm convert message và rabbit template
    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
