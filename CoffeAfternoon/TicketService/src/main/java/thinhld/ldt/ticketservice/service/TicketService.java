package thinhld.ldt.ticketservice.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import thinhld.ldt.ticketservice.conmon.model.TicketMessage;
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
    TicketRepo ticketRepo;

    @RabbitListener(queues = "queue.customer")
    private void receiveFromCustomerService(TicketMessage message) {
        updateTicketDate(message);
    }

    private void updateTicketDate(TicketMessage message) {
        try {

        } catch (Exception e) {
            log.info("Error update ticket date with ticket message from ticket service: " + message);
        }
    }

    /**
     * @return list Ticket in database
     * @apiNote API get all ticket
     */
    public ResponseEntity<?> getAllTicket() {
        try {
            TicketResponse ticketResponse = new TicketResponse();
            return new ResponseEntity<>(ticketResponse.convertDTO(ticketRepo.findAllByIsDeleteFalse()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình lấy list khách hàng", HttpStatus.OK);
        }
    }


}
