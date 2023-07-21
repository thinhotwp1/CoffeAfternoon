package thinhld.ldt.ticketservice.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import thinhld.ldt.ticketservice.conmon.config.UserConfig;
import thinhld.ldt.ticketservice.conmon.model.TicketMessage;
import thinhld.ldt.ticketservice.model.Ticket;
import thinhld.ldt.ticketservice.model.TicketRequest;
import thinhld.ldt.ticketservice.model.TicketResponse;
import thinhld.ldt.ticketservice.repository.TicketRepo;

import java.util.Date;

import static thinhld.ldt.ticketservice.config.RabbitMQConfiguration.ROUTING_TICKET;


@Service
@Log4j2(topic = "TicketService")
public class TicketService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange ticket_exchange;

    @Autowired
    TicketRepo ticketRepo;

    @RabbitListener(queues = "queue.ticket.customer")
    private void receiveFromCustomerService(TicketMessage message) {
        addTicketMonth(message);
    }

    private void addTicketMonth(TicketMessage message) {
        try {
            log.info("message add Ticket Month: " + message);
            ModelMapper modelMapper = new ModelMapper();
            Ticket ticket = modelMapper.map(message, Ticket.class);
            Date date = new Date();
            ticket.setLastUpdate(date.toString());
            ticket.setUserCurrent(UserConfig.userNameCurrent);
            System.out.println("Save ticket: " + ticket);
            ticketRepo.saveAndFlush(ticket);

            //send to bed service hire bed success
            sendRabbit(message);
            log.info("Send message to bed service, data: " + message);
        } catch (Exception e) {
            log.info("Error update ticket date with message from customer service: " + message + "\nDetail error: " + e);
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

    void sendRabbit(TicketMessage message) {
        rabbitTemplate.convertAndSend(ticket_exchange.getName(), ROUTING_TICKET, message);
    }

}
