package thinhld.ldt.userservice.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import thinhld.ldt.userservice.conmon.Message;
import thinhld.ldt.userservice.conmon.UserType;
import thinhld.ldt.userservice.model.User;
import thinhld.ldt.userservice.model.UserRequest;
import thinhld.ldt.userservice.repository.UserRepo;

import java.util.List;

import static thinhld.ldt.userservice.config.RabbitMQConfiguration.ROUTING_USER;


@Service
@Log4j2(topic = "UserService")
public class UserService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange user_exchange;

    @Autowired
    UserRepo userRepo;

    /**
     *
     * @param userRequest
     * @apiNote API đăng ký tài khoản => Send user current tới các service khác
     * @return user đã đăng ký nếu thành công hoặc error nếu thất bại
     */
    public ResponseEntity<?> signIn(UserRequest userRequest) {
        try {
            User user = userRepo.findUserByUserName(userRequest.getUser());
            if (userRequest.getPass().equals(user.getPassword())) {
                log.info("User sign In: " + user);

                //send user current
                Message message = new Message();
                message.setRole(user.getType());
                sendRabbit(message);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        } catch (Exception ignored) {
            log.info("Exception sign in: " + ignored);
            return new ResponseEntity<>(ignored, HttpStatus.EXPECTATION_FAILED);
        }
        log.info("sign In not found user");
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    /**
     *
     * @param userRequest
     * @apiNote API đăng nhập tài khoản
     * @return thành công hoặc thất bại
     */

    public ResponseEntity<?> signUn(UserRequest userRequest) {
        try {
            if (userRepo.findAllByUserName(userRequest.getUser()).size() > 0) {
                return new ResponseEntity<>("User is already register ! ", HttpStatus.ALREADY_REPORTED);
            }else {
                userRepo.save(userRequest.convertDTO(userRequest));
                return new ResponseEntity<>("User sign up success ! ", HttpStatus.OK);
            }

        } catch (Exception e) {
            log.info(e);
        }
        return new ResponseEntity<>("User sign up fail ! ", HttpStatus.EXPECTATION_FAILED);
    }


    /**
     * @apiNote send message rabbit to another service
     * @param message
     */
    void sendRabbit(Message message) {
        message.setTopic("User Service");
        rabbitTemplate.convertAndSend(user_exchange.getName(), ROUTING_USER, message);
    }

    /**
     *
      * @return all User
     */
    public List<User> getAllUser() {
        return userRepo.findAll();
    }
}
