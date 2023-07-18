package thinhld.ldt.customerservice.model;

import lombok.Data;
import thinhld.ldt.customerservice.controller.CustomerController;

import java.util.Date;


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
        Date date = new Date();
        Long currentTime = date.getTime();
        customer.setLastUpdate(currentTime.toString());
        customer.setUserCurrent(CustomerController.userNameCurrent);
        return customer;
    }
}