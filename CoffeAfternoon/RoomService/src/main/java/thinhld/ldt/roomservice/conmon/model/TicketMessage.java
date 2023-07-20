package thinhld.ldt.roomservice.conmon.model;

import lombok.Data;

@Data
public class TicketMessage {
    private String phoneNumber;
    private String bedId;
    private int typeTicket;
}
