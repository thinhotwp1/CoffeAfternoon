package thinhld.ldt.ticketservice.model;

import lombok.Data;
import org.modelmapper.ModelMapper;
import thinhld.ldt.ticketservice.conmon.config.UserConfig;

import java.util.Date;


@Data
public class TicketRequest {    
    private String phoneNumber;
    private String customerName;
    private int typeTicket;
    private boolean isDelete;

    public Ticket convertDTO(TicketRequest request) {
        // Map value TicketRequest to customer
        ModelMapper modelMapper = new ModelMapper();
        Ticket customer = modelMapper.map(request, Ticket.class);

        // update time and user
        Date date = new Date();
        Long currentTime = date.getTime();
        customer.setLastComing(currentTime.toString());
        customer.setLastUpdate(currentTime.toString());
        customer.setUserCurrent(UserConfig.userNameCurrent);
        return customer;
    }
}