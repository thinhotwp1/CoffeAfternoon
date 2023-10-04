package thinhld.ldt.ticketservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketRequest {    
    private String phoneNumber;
    private String customerName;
    private int typeTicket;
    private boolean isDelete;


}