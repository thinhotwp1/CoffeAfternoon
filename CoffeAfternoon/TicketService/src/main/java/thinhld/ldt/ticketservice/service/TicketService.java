package thinhld.ldt.ticketservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import thinhld.ldt.ticketservice.conmon.model.TicketMessage;
import thinhld.ldt.ticketservice.log.SystemLog;
import thinhld.ldt.ticketservice.log.TypeLog;
import thinhld.ldt.ticketservice.model.Ticket;
import thinhld.ldt.ticketservice.model.TicketType;
import thinhld.ldt.ticketservice.model.dto.MonthReport;
import thinhld.ldt.ticketservice.model.dto.QuarterReport;
import thinhld.ldt.ticketservice.model.dto.RequestReport;
import thinhld.ldt.ticketservice.model.dto.ResponseReport;
import thinhld.ldt.ticketservice.repository.TicketRepo;
import thinhld.ldt.ticketservice.repository.TicketTypeRepo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static thinhld.ldt.ticketservice.config.RabbitMQConfiguration.ROUTING_TICKET;


@Service
public class TicketService {

    @Autowired
    TicketRepo ticketRepo;

    @Autowired
    TicketTypeRepo ticketTypeRepo;


    /**
     * @return list Ticket in database
     * @apiNote API get all ticket
     */
    public ResponseEntity<?> getAllTicketType() {
        try {
            return ResponseEntity.ok(ticketTypeRepo.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình lấy danh sách loại vé !", HttpStatus.OK);
        }
    }

    public ResponseEntity<?> addTypeTicket(TicketType ticketType) {
        try {
            return ResponseEntity.ok(ticketTypeRepo.saveAndFlush(ticketType));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình xử lý !", HttpStatus.OK);
        }
    }

    public ResponseEntity<?> deleteTypeTicket(TicketType ticketType) {
        try {
            ticketTypeRepo.deleteById(String.valueOf(ticketType.getTypeTicket()));
            return ResponseEntity.ok("Xóa thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình xử lý !", HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getReport(RequestReport requestReport) {
        ResponseReport responseReport = new ResponseReport();

        if (requestReport.getTypeReport() == MonthReport.MONTH_REPORT.getMoth()) {
            List<Ticket> ticketList = ticketRepo.findAll();
            Map<String, Long> map = new HashMap<>(); // Map: type ticket/total money for that type ticket
            for (Ticket ticket : ticketList) {
                TicketType typeTicket = ticket.getTypeTicket();

                if (typeTicket != null) {

                    // map data
                    if (map.get(typeTicket.getTypeTicket()) == null) {
                        map.put(String.valueOf(typeTicket.getTypeTicket()), typeTicket.getPrice());
                    } else {
                        map.replace(String.valueOf(typeTicket.getTypeTicket()), map.get(typeTicket.getTypeTicket()) + typeTicket.getPrice());
                    }
                }
            }

            responseReport.setTypeReport(MonthReport.MONTH_REPORT.getMoth());
            if (!map.isEmpty()) responseReport.setDetails(map);
            else responseReport.setDetails(MonthReport.MONTH_REPORT.getDetails());

            return ResponseEntity.ok(responseReport);
        }

        throw new RuntimeException("Vui lòng chọn đúng loại báo cáo cần lấy !");
    }

    public ResponseEntity<?> addTicketTest(Ticket request) {
        try {
            ticketRepo.saveAndFlush(request);
            return ResponseEntity.ok("Thành công !");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Thất bại !");
        }
    }

    public ResponseEntity<?> getListTicket() {
        return ResponseEntity.ok(ticketRepo.findAll());
    }
}
