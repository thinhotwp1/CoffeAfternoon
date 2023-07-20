package thinhld.ldt.customerservice.model;

import jakarta.persistence.Column;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerResponse {
    private String phoneNumber;
    private String customerName;
    private String lastUpdate;
    private String userCurrent;
    private int typeTicket;
    private String bedId;
    private int ticketId;

    public CustomerResponse() {
    }

    public CustomerResponse(String phoneNumber, String customerName, int typeTicket, String lastUpdate, String userCurrent ) {
        this.phoneNumber = phoneNumber;
        this.customerName = customerName;
        this.typeTicket = typeTicket;
        this.lastUpdate = lastUpdate;
        this.userCurrent = userCurrent;
    }

    public List<CustomerResponse> convertDTO(List<Customer> customers) {
        List<CustomerResponse> responses = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        // Auto map 2 object
        for (Customer customer : customers) {
            responses.add(modelMapper.map(customer, CustomerResponse.class));
        }
        return responses;
    }
}
