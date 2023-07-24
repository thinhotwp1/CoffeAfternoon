package thinhld.ldt.userservice.conmon.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import thinhld.ldt.userservice.conmon.Message;
import thinhld.ldt.userservice.conmon.UserType;

@Log4j2
@Component
public class UserConfig {

    public static String userNameCurrent = "";
    public static int role = 0;


}
