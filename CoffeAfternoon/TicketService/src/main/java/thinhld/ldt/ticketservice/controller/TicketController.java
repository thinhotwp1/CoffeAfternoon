package thinhld.ldt.ticketservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thinhld.ldt.ticketservice.model.Ticket;
import thinhld.ldt.ticketservice.model.TicketType;
import thinhld.ldt.ticketservice.model.dto.RequestReport;
import thinhld.ldt.ticketservice.service.TicketService;


@RestController
@RequestMapping("/ticket")
@Log4j2
public class TicketController {
    @Autowired
    TicketService ticketService;
    @GetMapping("/get-type-ticket")
    public ResponseEntity<?> getListTicketType() {
        return ticketService.getAllTicketType();
    }
    @PostMapping("/add-type-ticket")
    public ResponseEntity<?> addTypeTicket(@RequestBody TicketType ticketType) {
        return ticketService.addTypeTicket(ticketType);
    }
    @PostMapping("/delete-type-ticket")
    public ResponseEntity<?> deleteTypeTicket(@RequestBody TicketType ticketType) {
        return ticketService.deleteTypeTicket(ticketType);
    }
    @PostMapping("/get-report-ticket")
    public ResponseEntity<?> getReport(@RequestBody RequestReport requestReport) {
        return ticketService.getReport(requestReport);
    }
    @PostMapping("/add-ticket")
    public ResponseEntity<?> addTicketTest(@RequestBody Ticket request) {
        return ticketService.addTicketTest(request);
    }
    @GetMapping("/get-ticket")
    public ResponseEntity<?> getListTicket() {
        return ticketService.getListTicket();
    }

}
