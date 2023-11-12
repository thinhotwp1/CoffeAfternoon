package thinhld.ldt.customerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerRequest {    
    private String phoneNumber;
    private String customerName;
    private long typeTicketId;
    private long bedId;

    public Customer convertDTO(CustomerRequest request) {
        // Map value CustomerRequest to customer
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(request, Customer.class);
    }
}