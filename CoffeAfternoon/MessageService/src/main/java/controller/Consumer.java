package controller;


import conmon.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    //Direct exchange
    @RabbitListener(queues = "queue.USER")
    private void receiveFromUserService(Message message){
        log.info("Message recevied from queue.USER->{}",message);
    }


}
