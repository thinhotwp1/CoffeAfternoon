package config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    // Khai báo các object
    public static final String QUEUE_USER = "queue.USER";
    public static final String ROUTING_USER = "routing.USER";
    public static final String USER_DIRECT_EXCHANGE = "exchange.USER";


    @Bean
    Queue queueA() {
        return new Queue(QUEUE_USER, false);
    }

    @Bean
    DirectExchange exchange_direct(){
        return new DirectExchange(USER_DIRECT_EXCHANGE);
    }


//      DirectExchange binding
    @Bean
    Binding bindingA(Queue queueA,DirectExchange exchange_direct){
        return BindingBuilder.bind(queueA).to(exchange_direct).with(ROUTING_USER);
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
