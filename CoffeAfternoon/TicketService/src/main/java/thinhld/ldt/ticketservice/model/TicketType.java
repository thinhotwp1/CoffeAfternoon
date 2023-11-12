package thinhld.ldt.ticketservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "t_ticket_type")
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "ticketTypeName")
    private String ticketTypeName;
    @Column(name = "dateTicket")
    private int dateTicket;
    @Column(name = "price")
    private long price;
    // one room to many Ticket
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idTypeTicket")
    private List<Ticket> ticketList;

}
