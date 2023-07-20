package thinhld.ldt.ticketservice.model;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Data
public class TicketResponse {
    private String phoneNumber;
    private String customerName;
    private int typeTicket;
    private boolean isDelete;
    private String lastUpdate;
    private String userCurrent;
    private String lastComing;

    public TicketResponse() {
    }

    public TicketResponse(String phoneNumber, String customerName, int typeTicket, String lastUpdate, String userCurrent, String lastComing) {
        this.phoneNumber = phoneNumber;
        this.customerName = customerName;
        this.typeTicket = typeTicket;
        this.lastUpdate = lastUpdate;
        this.userCurrent = userCurrent;
        this.lastComing = lastComing;
    }

    public List<TicketResponse> convertDTO(List<Ticket> customers) {
        List<TicketResponse> responses = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        // Auto map 2 object
        for (Ticket customer : customers) {
            responses.add(modelMapper.map(customer, TicketResponse.class));
        }
        return responses;
    }
}
