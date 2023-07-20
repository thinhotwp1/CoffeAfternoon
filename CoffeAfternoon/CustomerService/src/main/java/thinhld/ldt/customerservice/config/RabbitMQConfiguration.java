package thinhld.ldt.customerservice.config;

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
    public static final String QUEUE_CUSTOMER = "queue.customer";
    public static final String ROUTING_CUSTOMER = "routing.customer";
    public static final String CUSTOMER_EXCHANGE = "exchange.customer";

    // Khai báo các object
    public static final String QUEUE_USER = "queue.user";
    public static final String ROUTING_USER= "routing.user";
    public static final String USER_EXCHANGE = "exchange.user";

    @Bean
    Queue queueCustomer() {
        return new Queue(QUEUE_CUSTOMER, false);
    }
    @Bean
    TopicExchange customer_exchange() {
        return new TopicExchange(CUSTOMER_EXCHANGE);
    }

    //      DirectExchange binding
    @Bean
    Binding bindingCustomer() {
        return BindingBuilder.bind(queueCustomer()).to(customer_exchange()).with(ROUTING_CUSTOMER);
    }
    @Bean
    Queue queueUser() {
        return new Queue(QUEUE_USER, false);
    }

    @Bean
    TopicExchange user_exchange(){
        return new TopicExchange(USER_EXCHANGE);
    }

    //      DirectExchange binding
    @Bean
    Binding bindingUser() {
        return BindingBuilder.bind(queueUser()).to(user_exchange()).with(ROUTING_USER);
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
