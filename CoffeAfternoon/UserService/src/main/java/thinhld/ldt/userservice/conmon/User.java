package thinhld.ldt.userservice.conmon;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class User {
    private String userName;
    private int type;
    private String branch;
}
