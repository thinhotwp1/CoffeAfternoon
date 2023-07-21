package thinhld.ldt.bedservice.conmon.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import thinhld.ldt.bedservice.conmon.model.Message;
import thinhld.ldt.bedservice.conmon.model.UserType;

@Log4j2
@Component
public class UserConfig {

    public static String userNameCurrent = "";
    public static int role = 0;

    @RabbitListener(queues = "queue.bed.user")
    private void receiveFromA(Message message) {
        userNameCurrent = message.getUser();
        role = message.getRole();
        log.info("User current: " + UserConfig.userNameCurrent + ", role: " + UserType.fromValue(role));
    }
}
