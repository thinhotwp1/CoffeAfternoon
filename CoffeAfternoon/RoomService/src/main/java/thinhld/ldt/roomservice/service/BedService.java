package thinhld.ldt.roomservice.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import thinhld.ldt.roomservice.conmon.Message;
import thinhld.ldt.roomservice.model.Bed;
import thinhld.ldt.roomservice.model.BedRequest;
import thinhld.ldt.roomservice.model.BedResponse;
import thinhld.ldt.roomservice.repository.BedRepo;

import static thinhld.ldt.roomservice.config.RabbitMQConfiguration.ROUTING_BED;


@Service
@Log4j2(topic = "BedService")
public class BedService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange bed_exchange;

    @Autowired
    BedRepo bedRepo;

    /**
     * @return list Bed in database
     * @apiNote API get all bed:
     */
    public ResponseEntity<?> getAllBed() {
        try {
            BedResponse bedResponse = new BedResponse();
            return new ResponseEntity<>(bedResponse.convertDTO(bedRepo.findAllByIsDeleteFalse()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình lấy danh sách giường !", HttpStatus.OK);
        }
    }

    public ResponseEntity<?> addRoom(BedRequest bedRequest) {
        try {
            Bed bed = bedRequest.convertDTO(bedRequest);
            bedRepo.save(bed);
            return new ResponseEntity<>("Thêm giường " + bed.getBedName() + " thành công !", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình thêm giường !\n Detail: " + e, HttpStatus.OK);
        }
    }


    void sendRabbit(Message message) {
        message.setTopic("User Service");
        rabbitTemplate.convertAndSend(bed_exchange.getName(), ROUTING_BED, message);
    }
}
