package thinhld.ldt.ticketservice.conmon.model;

import lombok.Data;

import java.util.Calendar;

@Data
public class TicketMessage {
    private String phoneNumber;
    private String customerName;
    private String bedId;
    private int typeTicket;
    private Calendar dateTicket;

}
