package thinhld.ldt.customerservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_cuscomer"
        , indexes = {@Index(name = "idx_phoneNumber", columnList = "phoneNumber")}
)
public class Customer {
    @Id
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "customerName")
    private String customerName;
    @Column(name = "type")
    private int type;
    @Column(name = "isDelete")
    private boolean isDelete = false;

    public Customer() {
    }

    public Customer(String phoneNumber, String customerName, int type) {
        this.phoneNumber = phoneNumber;
        this.customerName = customerName;
        this.type = type;
    }

}
