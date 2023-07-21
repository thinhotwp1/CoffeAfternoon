package thinhld.ldt.customerservice.conmon.model;

import lombok.Data;

import java.util.Calendar;

@Data
public class TicketMessage {
    private String topic;
    private String phoneNumber;
    private String customerName;
    private Long bedId;
    private int typeTicket;
    private Calendar dateTicket;
}
