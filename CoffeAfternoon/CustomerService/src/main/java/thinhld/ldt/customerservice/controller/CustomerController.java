package thinhld.ldt.customerservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thinhld.ldt.customerservice.conmon.Message;
import thinhld.ldt.customerservice.conmon.UserType;
import thinhld.ldt.customerservice.model.Customer;
import thinhld.ldt.customerservice.model.CustomerRequest;
import thinhld.ldt.customerservice.service.CustomerService;

import java.util.List;


@RestController
@RequestMapping("/customer")
@Log4j2
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/test")
    public String testAPI() {
        return "Success";
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getListCustomer() {
        return customerService.getAllCustomer();
    }

    @PostMapping("/find-by-name")
    public ResponseEntity<?> findCustomersByName(@RequestBody CustomerRequest request) {
        return customerService.findCustomersByName(request);
    }

    @PostMapping("/find-by-phone")
    public ResponseEntity<?> findCustomersByPhone(@RequestBody CustomerRequest request) {
        return customerService.findCustomersByPhone(request);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerRequest request) {
        return customerService.addCustomer(request);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteCustomer(@RequestBody Customer request) {
        return customerService.deleteCustomer(request);
    }

    @GetMapping("/get-all-delete")
    public ResponseEntity<?> getListCustomerDeleted() {
        return customerService.getAllCustomerDeleted();
    }
    @PostMapping("/find-deleted-by-phone")
    public ResponseEntity<?> finddeletedCustomersByPhone(@RequestBody CustomerRequest request) {
        return customerService.findDeletedCustomersByPhone(request);
    }
    @PostMapping("/find-deleted-by-name")
    public ResponseEntity<?> findDeletedCustomersByName(@RequestBody CustomerRequest request) {
        return customerService.findDeletedCustomersByName(request);
    }
    @PostMapping("/revert")
    public ResponseEntity<?> revertCustomer(@RequestBody List<Customer> request) {
        return customerService.revertCustomer(request);
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer request) {
        return customerService.updateCustomer(request);
    }

}
