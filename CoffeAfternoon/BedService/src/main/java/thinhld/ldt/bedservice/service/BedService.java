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
import thinhld.ldt.bedservice.model.ListBedHire;
import thinhld.ldt.bedservice.repository.BedRepo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
            bed.setCustomerId(message.getPhoneNumber());
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
            List<Bed> bedList = bedRepo.findAllByIsDeleteFalse();
            ListBedHire listBedHire = new ListBedHire();
            List<Bed> listActive = new ArrayList<>();
            List<Bed> listUsed = new ArrayList<>();

            // Get all bed used and active
            Calendar calendarCurrent = Calendar.getInstance();
            Date currentTime = new Date();
            calendarCurrent.setTime(currentTime);

            for (Bed bed : bedList) {
                if (bed.getDateTicket() != null && bed.getDateTicket().after(calendarCurrent)) {
                    listUsed.add(bed);
                } else {
                    listActive.add(bed);
                }
            }
            listBedHire.setListUsed(listUsed);
            listBedHire.setListActive(listActive);

            return new ResponseEntity<>(listBedHire, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error get all box: " + e);
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình lấy danh sách giường !", HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<?> addBed(BedRequest bedRequest) {
        try {
            Bed bed = bedRequest.convertDTO(bedRequest);
            System.out.println("BED: " + bed);
            bedRepo.save(bed);
            return new ResponseEntity<>("Thêm giường " + bed.getBedName() + " thành công !", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error add box: " + e);
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình thêm giường !", HttpStatus.EXPECTATION_FAILED);
        }
    }


    /**
     * @return list Bed in database
     * @apiNote API get all bed
     */
    public ResponseEntity<?> hireBed(Bed bedRequest) {
        try {
            // set time ticket-day
            Date currentTime = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentTime);
            // Cộng thêm số tháng theo loại vé vào Calendar
            calendar.add(Calendar.HOUR_OF_DAY, 4);
            bedRequest.setDateTicket(calendar);
            bedRepo.saveAndFlush(bedRequest);

            return new ResponseEntity<>("Thuê thành công box " + bedRequest.getBedName() + ", thời gian sử dụng còn 4 giờ. ", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error hire box: " + e);
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình thuê box " + bedRequest.getBedName(), HttpStatus.EXPECTATION_FAILED);
        }
    }

}
