package thinhld.ldt.ticketservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "t_ticket_type")
public class TicketType {
    @Id
    @Column(name = "typeTicket")
    private int typeTicket;
    @Column(name = "ticketTypeName")
    private String ticketTypeName;
    @Column(name = "price")
    private long price;

}
