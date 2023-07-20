package thinhld.ldt.ticketservice.conmon.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import thinhld.ldt.ticketservice.conmon.model.Message;

@Log4j2
@Component
public class UserConfig {

    public static String userNameCurrent = "";
    public static int role = 0;

    @RabbitListener(queues = "queue.user")
    private void receiveFromA(Message message) {
        UserConfig.userNameCurrent = message.getUser();
        UserConfig.role = message.getRole();
        log.info("User current: " + UserConfig.userNameCurrent);
    }
}
