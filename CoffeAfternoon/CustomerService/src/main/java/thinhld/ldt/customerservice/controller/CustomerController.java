package thinhld.ldt.customerservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thinhld.ldt.customerservice.model.Customer;
import thinhld.ldt.customerservice.model.CustomerRequest;
import thinhld.ldt.customerservice.service.CustomerService;

import java.util.List;


@RestController
@RequestMapping("/customer")
@Log4j2
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getListCustomer() {
        return customerService.getAllCustomer();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerRequest request) {
        return customerService.addCustomer(request);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteCustomer(@RequestBody Customer request) {
        return customerService.deleteCustomer(request);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer request) {
        return customerService.updateCustomer(request);
    }

    @PostMapping("/find-by-name")
    public ResponseEntity<?> findCustomersByName(@RequestBody CustomerRequest request) {
        return customerService.findCustomersByName(request);
    }

    @PostMapping("/find-by-phone")
    public ResponseEntity<?> findCustomersByPhone(@RequestBody CustomerRequest request) {
        return customerService.findCustomersByPhone(request);
    }

}
