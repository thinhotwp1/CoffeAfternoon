package thinhld.ldt.customerservice.model;

import jakarta.persistence.Column;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Data
public class CustomerResponse {
    private String phoneNumber;
    private String customerName;
    private String bedId;
    private int ticketId;
    private Calendar dateTicket;

    public CustomerResponse() {
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
