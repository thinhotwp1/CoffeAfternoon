package thinhld.ldt.bedservice.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import thinhld.ldt.bedservice.conmon.model.Message;
import thinhld.ldt.bedservice.conmon.config.UserConfig;
import thinhld.ldt.bedservice.conmon.model.TicketMessage;
import thinhld.ldt.bedservice.model.Bed;
import thinhld.ldt.bedservice.model.BedRequest;
import thinhld.ldt.bedservice.model.BedResponse;
import thinhld.ldt.bedservice.repository.BedRepo;

import static thinhld.ldt.bedservice.config.RabbitMQConfiguration.ROUTING_BED;


@Service
@Log4j2(topic = "BedService")
public class BedService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    BedRepo bedRepo;

    @RabbitListener(queues = "queue.bed.ticket")
    private void receiveFromCustomerService(TicketMessage message) {
        updateBedTicket(message);
    }

    private void updateBedTicket(TicketMessage message) {
        try {
            Bed bed = bedRepo.findByIdIs(message.getBedId());
            System.out.println("Bed find: " + bed);
            bed.setCustomerId(message.getPhoneNumber());
            bed.setUsing(true);
            bed.setDateTicket(message.getDateTicket());
            bedRepo.saveAndFlush(bed);
            log.info("Success update bed ticket date with ticket message from ticket service: " + message);
        } catch (Exception e) {
            log.info("Error update ticket date with ticket message from ticket service: " + message);
        }
    }

    /**
     * @return list Bed in database
     * @apiNote API get all bed
     */
    public ResponseEntity<?> getAllBed() {
        try {
            BedResponse bedResponse = new BedResponse();
            return new ResponseEntity<>(bedResponse.convertDTO(bedRepo.findAllByIsDeleteFalse()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình lấy danh sách giường !", HttpStatus.OK);
        }
    }

    public ResponseEntity<?> addBed(BedRequest bedRequest) {
        try {
            Bed bed = bedRequest.convertDTO(bedRequest);
            bedRepo.save(bed);
            return new ResponseEntity<>("Thêm giường " + bed.getBedName() + " thành công !", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình thêm giường !\n Detail: " + e, HttpStatus.OK);
        }
    }

//    public ResponseEntity<?> hireBed(Bed bedRequest) {
//        try {
//            bedRequest.setUsing(true);
//            bedRepo.saveAndFlush(bedRequest);
//
//            // send message
//            Message message = new Message();
//            message.setUser(UserConfig.userNameCurrent);
//            message.setRole(UserConfig.role);
//            message.setDescription(Long.toString(bedRequest.getId()));
//            sendRabbit(message);
//            return new ResponseEntity<>(bedRequest, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Xảy ra lỗi trong quá trình thuê giường: " + bedRequest.getBedName(), HttpStatus.OK);
//        }
//    }


//    void sendRabbit(Message message) {
//        message.setTopic("Room Service");
//        rabbitTemplate.convertAndSend(bed_exchange.getName(), ROUTING_BED, message);
//    }
}
