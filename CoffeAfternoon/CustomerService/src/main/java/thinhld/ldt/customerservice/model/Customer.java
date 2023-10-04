package thinhld.ldt.customerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "t_customer"
        , indexes = {@Index(name = "idx_phoneNumber", columnList = "phoneNumber")}
)
public class Customer extends BaseEntity{
    @Id
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "customerName")
    private String customerName;
    @Column(name = "bedId")
    private String bedId;
    @Column(name = "ticketId")
    private int ticketId;
    @Column(name = "typeTicket")
    private int typeTicket;
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

}
