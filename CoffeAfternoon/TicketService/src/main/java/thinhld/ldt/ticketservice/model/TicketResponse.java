package thinhld.ldt.ticketservice.model;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Data
public class TicketResponse {
    private String phoneNumber;
    private String customerName;
    private String bedId;
    private int typeTicket;
    private Calendar dateTicket;
    private String lastUpdate;
    private String userCurrent;

    public TicketResponse() {
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
