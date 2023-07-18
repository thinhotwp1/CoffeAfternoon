package thinhld.ldt.customerservice.model;

import lombok.Data;

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

    public CustomerResponse(String phoneNumber, String customerName, int type , String lastUpdate, String userCurrent, String lastComing) {
        this.phoneNumber = phoneNumber;
        this.customerName = customerName;
        this.type = type;
        this.lastUpdate = lastUpdate;
        this.userCurrent = userCurrent;
        this.lastComing = lastComing;
    }

    public List<CustomerResponse> convertDTO(List<Customer> customers) throws Exception{
        List<CustomerResponse> responses = new ArrayList<>();

        for (Customer customer : customers) {
            responses.add(new CustomerResponse(customer.getPhoneNumber(), customer.getCustomerName(), customer.getType(), customer.getLastUpdate(), customer.getUserCurrent(), customer.getLastComing()));
        }
        return responses;
    }
}
