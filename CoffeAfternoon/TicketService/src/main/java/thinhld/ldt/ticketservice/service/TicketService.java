package thinhld.ldt.ticketservice.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import thinhld.ldt.ticketservice.model.Ticket;
import thinhld.ldt.ticketservice.model.TicketRequest;
import thinhld.ldt.ticketservice.model.TicketResponse;
import thinhld.ldt.ticketservice.repository.TicketRepo;


@Service
@Log4j2(topic = "TicketService")
public class TicketService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    TicketRepo customerRepo;

    /**
     * @return list Ticket in database
     * @apiNote API get all customer
     */
    public ResponseEntity<?> getAllTicket() {
        try {
            TicketResponse customerResponse = new TicketResponse();
            return new ResponseEntity<>(customerResponse.convertDTO(customerRepo.findAllByIsDeleteFalse()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình lấy list khách hàng", HttpStatus.OK);
        }
    }


}
