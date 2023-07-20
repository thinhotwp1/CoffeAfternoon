package thinhld.ldt.customerservice.model;

import lombok.Data;
import org.modelmapper.ModelMapper;
import thinhld.ldt.customerservice.conmon.config.UserConfig;

import java.util.Date;


@Data
public class CustomerRequest {    
    private String phoneNumber;
    private String customerName;
    private int typeTicket;
    private Long bedId;

    public Customer convertDTO(CustomerRequest request) {
        // Map value CustomerRequest to customer
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = modelMapper.map(request, Customer.class);

        // update time and user
        Date date = new Date();
        Long currentTime = date.getTime();
        customer.setLastUpdate(currentTime.toString());
        customer.setUserCurrent(UserConfig.userNameCurrent);
        return customer;
    }
}