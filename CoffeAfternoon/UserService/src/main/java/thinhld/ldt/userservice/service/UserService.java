package thinhld.ldt.userservice.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.DirectExchange;
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

@Service
@Log4j2(topic = "UserService")
public class UserService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange exchange_direct;

    @Autowired
    UserRepo userRepo;

    void sendRabbit(String data) {
        Message message = new Message();
        message.setDescription("UserService signIn: " + data);
        rabbitTemplate.convertAndSend(exchange_direct.getName(), "routing.A", message);
    }

    public ResponseEntity<?> signIn(UserRequest request) {
        try {
            User user = userRepo.findUserByUserName(request.getUser());
            if (request.getPass().equals(user.getPassword())) {
                log.info("UserService signIn: " + user);

                // Send message to message service
                sendRabbit("UserService signIn: " + user);

                // Return data
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        } catch (Exception ignored) {
            log.info("Exception: " + ignored);

            // Send message to message service
            sendRabbit("UserService Exception: " + ignored);

            return new ResponseEntity<>(ignored, HttpStatus.EXPECTATION_FAILED);
        }
        log.info("signIn not found user");

        // Send message to message service
        sendRabbit("UserService signIn not found user");

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<?> signUn(UserRequest request) {
        try {
            if (userRepo.findAllByUserName(request.getUser()).size() > 0) {
                // Send message to message service
                sendRabbit("UserService is already register ! ");
                return new ResponseEntity<>("UserService is already register ! ", HttpStatus.ALREADY_REPORTED);
            }else {
                userRepo.save(request.convertDTO(request));
                return new ResponseEntity<>("UserService sign up success ! ", HttpStatus.OK);
            }

        } catch (Exception e) {
            log.info(e);
            // Send message to message service
            sendRabbit("UserService Exception: " + e);
        }

        sendRabbit("UserService sign up fail ! ");
        return new ResponseEntity<>("UserService sign up fail ! ", HttpStatus.EXPECTATION_FAILED);
    }
}
