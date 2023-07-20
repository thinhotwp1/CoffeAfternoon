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
    private UserRepo userRepo;

    /**
     * @param userRequest
     * @return user đã đăng ký nếu thành công hoặc error nếu thất bại
     * @apiNote API đăng ký tài khoản => Send user current tới các service khác
     */
    public ResponseEntity<?> signIn(UserRequest userRequest) {
        try {
            List<User> userList = userRepo.findUserByUserName(userRequest.getUser());
            if (userList.size() == 0) {
                log.info("Not found user with user name: " + userRequest.getUser());
                return new ResponseEntity<>("Sai tài khoản hoặc mật khẩu", HttpStatus.BAD_REQUEST);
            }
            User user = userList.get(0);
            if (userRequest.getPass().equals(user.getPassword())) {
                log.info("User sign In: " + user);

                //send user current
                Message message = new Message();
                message.setRole(user.getType());
                message.setUser(user.getUserName());
                message.setBranch(user.getBranch());
                sendRabbit(message);
                return new ResponseEntity<>("Đăng nhập thành công !", HttpStatus.OK);

            } else {
                log.info("Wrong password or user name");
                return new ResponseEntity<>("Sai tài khoản hoặc mật khẩu", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ignored) {
            log.info("Exception sign in: " + ignored);
            return new ResponseEntity<>(ignored, HttpStatus.EXPECTATION_FAILED);
        }
    }


    /**
     * @param userRequest
     * @return thành công hoặc thất bại
     * @apiNote API đăng nhập tài khoản
     */

    public ResponseEntity<?> signUn(UserRequest userRequest) {
        try {
            if (userRepo.findAllByUserName(userRequest.getUser()).size() > 0) {
                return new ResponseEntity<>("Tài khoản này đã được đăng ký ! ", HttpStatus.ALREADY_REPORTED);
            } else {
                userRepo.save(userRequest.convertDTO(userRequest));
                return new ResponseEntity<>("Đăng ký thành công tài khoản " + userRequest.getUser(), HttpStatus.OK);
            }
        } catch (Exception e) {
            log.info("User sign up fail : " + e);
            return new ResponseEntity<>("Gặp lỗi trình quá trình đăng ký ! \n Detail: " + e, HttpStatus.EXPECTATION_FAILED);
        }
    }


    /**
     * @param message
     * @apiNote send message rabbit to another service
     */
    void sendRabbit(Message message) {
        message.setTopic("User Service");
        rabbitTemplate.convertAndSend(user_exchange.getName(), ROUTING_USER, message);
    }

    /**
     * @return all User
     */
    public List<User> getAllUser() {
        return userRepo.findAll();
    }
}
