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
    @Column(name = "typeTicketId")
    private long typeTicketId;
    @Column(name = "ticketId")
    private long ticketId;

}
