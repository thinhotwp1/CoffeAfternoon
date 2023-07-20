package thinhld.ldt.customerservice.conmon.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import thinhld.ldt.customerservice.conmon.model.Message;

import java.util.Calendar;
import java.util.Date;

@Log4j2
@Component
public class UserConfig {

    public static String userNameCurrent = "";
    public static int role = 0;

    @RabbitListener(queues = "queue.user")
    private void receiveFromUserService(Message message) {
        UserConfig.userNameCurrent = message.getUser();
        UserConfig.role = message.getRole();
   }

}

