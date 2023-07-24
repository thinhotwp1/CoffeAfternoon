package thinhld.ldt.messageservice.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import thinhld.ldt.messageservice.model.Message;

@Component
@Slf4j
public class Consumer {

    //Direct exchange
    @RabbitListener(queues = "queue.customer.user")
    private void receiveFromA(Message message){
        log.info("Message recevied from QUEUE USER->{}",message);
    }

}
