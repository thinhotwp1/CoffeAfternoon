package thinhld.ldt.ticketservice.model.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ResponseReport {
    private int typeReport;
    private Map<String,Long> details; // Map: type ticket/total money for that type ticket
}
