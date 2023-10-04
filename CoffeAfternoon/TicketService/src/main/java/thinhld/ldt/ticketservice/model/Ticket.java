package thinhld.ldt.ticketservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "t_ticket"
        , indexes = {@Index(name = "idx_phoneNumber", columnList = "phoneNumber")}
)
public class Ticket {
    @Id
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "customerName")
    private String customerName;
    @Column(name = "bedId")
    private String bedId;
    @Column(name = "typeTicket")
    private int typeTicket;     // int month
    @Column(name = "dateTicket")
    private Calendar dateTicket;

    /**
     * API hire bed
     * 1. from customer service update bed_id in customer => send " phoneNumber and typeTicket"
     * to ticket service =>
     * ticket service: add new a ticket with type and phone number customer in message
     * (if step 1 fail send to bed service to cancel update bed object)
     * 2. from bed service: update phone number to id_customer and date ticket to bed_id
     *
     */

    @Column(name = "isDelete")
    private boolean isDelete = false;
    @Column(name = "lastUpdate")
    private String lastUpdate ;
    @Column(name = "userCurrent")
    private String userCurrent ;

    public Ticket() {
    }

}
