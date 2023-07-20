package thinhld.ldt.userservice.conmon;


import jakarta.persistence.Column;
import lombok.Data;

@Data
public class Message {
    private String topic;
    private String user;
    private int role;
    private String branch;
    private String description;
}
