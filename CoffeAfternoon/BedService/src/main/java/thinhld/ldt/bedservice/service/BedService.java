package thinhld.ldt.bedservice.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import thinhld.ldt.bedservice.conmon.model.TicketMessage;
import thinhld.ldt.bedservice.log.SystemLog;
import thinhld.ldt.bedservice.log.TypeLog;
import thinhld.ldt.bedservice.model.Bed;
import thinhld.ldt.bedservice.model.dto.BedRequest;
import thinhld.ldt.bedservice.model.dto.ListBedHire;
import thinhld.ldt.bedservice.repository.BedRepo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class BedService {

//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    @Autowired
    BedRepo bedRepo;

//    @RabbitListener(queues = "queue.bed.ticket")
//    private void receiveFromCustomerService(TicketMessage message) {
//        updateBedTicket(message);
//    }

    private void updateBedTicket(TicketMessage message) {
        try {
            Bed bed = bedRepo.findByIdIs(message.getBedId());
            bed.setCustomerId(message.getPhoneNumber());
            bed.setDateTicket(message.getDateTicket());
            bedRepo.saveAndFlush(bed);
            SystemLog.log("Success update bed ticket date with ticket message from ticket service: " + message, TypeLog.INFO);
        } catch (Exception e) {
            e.printStackTrace();
            SystemLog.log("Error update ticket date with ticket message from ticket service: " + message, TypeLog.INFO);
        }
    }

    /**
     * @return list Bed in database
     * @apiNote API get all bed
     */
    public ResponseEntity<?> getAllBed() {
        try {
            List<Bed> bedList = bedRepo.findAll();
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
            SystemLog.log("Error get all box: " + e, TypeLog.INFO);
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
            SystemLog.log("Error add box: " + e, TypeLog.INFO);
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
            Bed bed = bedRepo.findByIdIs(bedRequest.getId());

            Date currentTime = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentTime);
            // Cộng thêm số giờ cho vé ngày
            calendar.add(Calendar.HOUR_OF_DAY, 4);
            bed.setDateTicket(calendar);

            bedRepo.saveAndFlush(bed);
            return new ResponseEntity<>("Thuê thành công box " + bed.getBedName() + ", thời gian sử dụng còn 4 giờ. ", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình thuê box "+ e, HttpStatus.EXPECTATION_FAILED);
        }
    }

}
