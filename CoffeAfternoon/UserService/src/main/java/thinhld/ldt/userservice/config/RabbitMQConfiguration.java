package thinhld.ldt.userservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    // Khai báo các object customer
    public static final String QUEUE_CUSTOMER_USER = "queue.customer.user";
    public static final String ROUTING_CUSTOMER = "routing.customer";
    public static final String CUSTOMER_EXCHANGE = "exchange.customer";

    // Khai báo các object ticket
    public static final String QUEUE_TICKET_USER = "queue.ticket.user";
    public static final String QUEUE_TICKET_CUSTOMER = "queue.ticket.customer";
    public static final String ROUTING_TICKET = "routing.ticket";
    public static final String TICKET_EXCHANGE = "exchange.ticket";


    // Khai báo các object bed
    public static final String QUEUE_BED_TICKET = "queue.bed.ticket";
    public static final String QUEUE_BED_USER = "queue.bed.user";
    public static final String ROUTING_BED = "routing.bed";
    public static final String BED_EXCHANGE = "exchange.bed";


    // Khai báo các object user
    public static final String ROUTING_USER= "routing.user";
    public static final String USER_EXCHANGE = "exchange.user";


    /**
     *
     * @implNote Tạo các bean queue
     */
    @Bean
    Queue queueCustomerUser() {
        return new Queue(QUEUE_CUSTOMER_USER, false);
    }
    @Bean
    Queue queueBedUser() {
        return new Queue(QUEUE_BED_USER, false);
    }
    // ticket config
    @Bean
    Queue queueTicketUser() {
        return new Queue(QUEUE_TICKET_USER, false);
    }
    @Bean
    Queue queueTicketCustomer() {
        return new Queue(QUEUE_TICKET_CUSTOMER, false);
    }

    // bed config 
    @Bean
    Queue queueBedTicket() {
        return new Queue(QUEUE_BED_TICKET, false);
    }


    /**
     *
     * @implNote Tạo bean các topic exchange
     */

    @Bean
    TopicExchange user_exchange(){
        return new TopicExchange(USER_EXCHANGE);
    }
    @Bean
    TopicExchange customer_exchange() {
        return new TopicExchange(CUSTOMER_EXCHANGE);
    }
    @Bean
    TopicExchange ticket_exchange() {
        return new TopicExchange(TICKET_EXCHANGE);
    }

    /**
     *
     * @implNote binding các queue tới các hàng đợi
     */
    // Đăng ký queue ticket đăng ký tin nhắn của customer service
    @Bean
    Binding bindingCustomerTicket() {
        return BindingBuilder.bind(queueTicketCustomer()).to(customer_exchange()).with(ROUTING_CUSTOMER);
    }

    // Đăng ký queue bed đăng ký tin nhắn của ticket service
    @Bean
    Binding bindingTicketBed() {
        return BindingBuilder.bind(queueBedTicket()).to(ticket_exchange()).with(ROUTING_TICKET);
    }

    // Cho các service cùng đăng ký với topic user
    @Bean
    Binding bindingUserCustomer() {
        return BindingBuilder.bind(queueCustomerUser()).to(user_exchange()).with(ROUTING_USER);
    }
    @Bean
    Binding bindingUserTicket() {
        return BindingBuilder.bind(queueTicketUser()).to(user_exchange()).with(ROUTING_USER);
    }
    @Bean
    Binding bindingUserBed() {
        return BindingBuilder.bind(queueBedUser()).to(user_exchange()).with(ROUTING_USER);
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

