package thinhld.ldt.customerservice.conmon.model;


import lombok.Data;

@Data
public class Message {
    private String user;
    private int role;
    private String topic;
    private String description;
}
