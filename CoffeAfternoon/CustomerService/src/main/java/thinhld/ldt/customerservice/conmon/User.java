package thinhld.ldt.customerservice.conmon;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class User {
    private String userName;
    private int type;
    private String branch;
}
