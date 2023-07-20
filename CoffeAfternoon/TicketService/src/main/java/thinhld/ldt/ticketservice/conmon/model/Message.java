package thinhld.ldt.ticketservice.conmon.model;


import lombok.Data;

@Data
public class Message {
    private String user;
    private int role;
    private String topic;
    private String description;
}
