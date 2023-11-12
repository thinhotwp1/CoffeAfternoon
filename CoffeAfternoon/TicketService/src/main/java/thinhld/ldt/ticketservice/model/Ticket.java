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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "idTypeTicket")
    private long idTypeTicket;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "bedId")
    private String bedId;
    @Column(name = "dateTicket")
    private Calendar dateTicket;
    @Column(name = "price")
    private long price;
}
