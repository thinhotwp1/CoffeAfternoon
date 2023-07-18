package thinhld.ldt.customerservice.model;

import lombok.Data;

@Data
public class CustomerRequest {    
    private String phoneNumber;
    private String customerName;
    private int type;
    private boolean isDelete;

    public Customer convertDTO(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setCustomerName(request.getCustomerName());
        customer.setType(request.getType());
        customer.setPhoneNumber(request.getPhoneNumber());
        return customer;
    }
}