package thinhld.ldt.ticketservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "t_ticket")
public class Ticket {
    @Id
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "customerName")
    private String customerName;
    @Column(name = "bedId")
    private String bedId;
    // one ticket to one ticketType
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeTicket")
    private TicketType typeTicket;     // int month
    @Column(name = "dateTicket")
    private Calendar dateTicket;
    @Column(name = "price")
    private long price;

}
