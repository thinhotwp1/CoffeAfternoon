package thinhld.ldt.customerservice.conmon.model;

import lombok.Data;

import java.util.Calendar;

@Data
public class TicketMessage {
    private String phoneNumber;
    private String bedId;
    private int typeTicket;
    private Calendar dateTicket;
}
