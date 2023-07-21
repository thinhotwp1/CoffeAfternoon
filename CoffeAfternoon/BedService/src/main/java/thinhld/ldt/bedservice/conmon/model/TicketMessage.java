package thinhld.ldt.bedservice.conmon.model;

import lombok.Data;

import java.util.Calendar;

@Data
public class TicketMessage {
    private String phoneNumber;
    private Long bedId;
    private int typeTicket;
    private Calendar dateTicket;
}
