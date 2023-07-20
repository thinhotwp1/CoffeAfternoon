package thinhld.ldt.ticketservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thinhld.ldt.ticketservice.model.Ticket;
import thinhld.ldt.ticketservice.model.TicketRequest;
import thinhld.ldt.ticketservice.service.TicketService;

import java.util.List;


@RestController
@RequestMapping("/ticket")
@Log4j2
public class TicketController {
    @Autowired
    TicketService ticketService;

    @GetMapping("/test")
    public String testAPI() {
        return "Success";
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getListTicket() {
        return ticketService.getAllTicket();
    }

}
