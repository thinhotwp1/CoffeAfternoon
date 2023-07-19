package thinhld.ldt.customerservice.model;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerResponse {
    private String phoneNumber;
    private String customerName;
    private int type;
    private boolean isDelete;
    private String lastUpdate;
    private String userCurrent;
    private String lastComing;

    public CustomerResponse() {
    }

    public CustomerResponse(String phoneNumber, String customerName, int type, String lastUpdate, String userCurrent, String lastComing) {
        this.phoneNumber = phoneNumber;
        this.customerName = customerName;
        this.type = type;
        this.lastUpdate = lastUpdate;
        this.userCurrent = userCurrent;
        this.lastComing = lastComing;
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
