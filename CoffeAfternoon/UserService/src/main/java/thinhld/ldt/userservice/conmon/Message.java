package thinhld.ldt.userservice.conmon;


import lombok.Data;

@Data
public class Message {
    private String user;
    private String topic;
    private String description;
}
