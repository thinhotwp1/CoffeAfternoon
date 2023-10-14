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
import thinhld.ldt.ticketservice.conmon.model.TicketMessage;
import thinhld.ldt.ticketservice.log.SystemLog;
import thinhld.ldt.ticketservice.log.TypeLog;
import thinhld.ldt.ticketservice.model.Ticket;
import thinhld.ldt.ticketservice.model.TicketRequest;
import thinhld.ldt.ticketservice.model.TicketResponse;
import thinhld.ldt.ticketservice.repository.TicketRepo;

import java.util.Date;

import static thinhld.ldt.ticketservice.config.RabbitMQConfiguration.ROUTING_TICKET;


@Service
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
            SystemLog.log("message add Ticket Month: " + message, TypeLog.INFO);
            ModelMapper modelMapper = new ModelMapper();
            Ticket ticket = modelMapper.map(message, Ticket.class);
            Date date = new Date();
            SystemLog.log("Save ticket: " + ticket, TypeLog.INFO);
            ticketRepo.saveAndFlush(ticket);

            //send to bed service hire bed success
            sendRabbit(message);
            SystemLog.log("Send message to bed service, data: " + message, TypeLog.INFO);
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình lấy list khách hàng", HttpStatus.OK);
        }
    }

    void sendRabbit(TicketMessage message) {
        rabbitTemplate.convertAndSend(ticket_exchange.getName(), ROUTING_TICKET, message);
    }
}
