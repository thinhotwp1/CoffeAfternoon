package thinhld.ldt.customerservice.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import thinhld.ldt.customerservice.conmon.Message;
import thinhld.ldt.customerservice.model.Customer;
import thinhld.ldt.customerservice.model.CustomerRequest;
import thinhld.ldt.customerservice.repository.CustomerRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static thinhld.ldt.customerservice.config.RabbitMQConfiguration.ROUTING_USER;


@Service
@Log4j2(topic = "CustomerService")
public class CustomerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    CustomerRepo customerRepo;


    public ResponseEntity<?> getAllCustomer() {
        try {
            return new ResponseEntity<>(customerRepo.findAllByIsDeleteFalse(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình lấy list khách hàng", HttpStatus.OK);
        }
    }

    /**
     * @param customerRequest
     * @return success if added or error if error
     * @apiNote API add customer:
     * Validate exited customer
     * => Add customer if not exited
     */
    public ResponseEntity<?> addCustomer(CustomerRequest customerRequest) {
        try {
            // validate customer
            if (customerRepo.findAllByPhoneNumberAndIsDeleteFalse(customerRequest.getPhoneNumber()).size() > 0) {
                log.info("Add Customer: Exited Customer !");
                return new ResponseEntity<>("Đã tồn tại khách hàng đã đăng ký bằng số điện thoại này ! ", HttpStatus.ALREADY_REPORTED);
            }
            // add customer
            customerRepo.save(customerRequest.convertDTO(customerRequest));
            log.info("Success Added Customer: " + customerRequest);
            return new ResponseEntity<>("Thêm thành công khách hàng " + customerRequest.getCustomerName() + " với số điện thoại " + customerRequest.getPhoneNumber(), HttpStatus.OK);

        } catch (Exception ignored) {
            log.info("Exception Added Customer: " + ignored);
            return new ResponseEntity<>("Có lỗi trong quá trình thêm khách hàng ! \nDetail: " + ignored, HttpStatus.EXPECTATION_FAILED);
        }
    }


    /**
     * @param customerRequest
     * @return success if added or error if error
     * @apiNote API add customer:
     * Validate exited customer
     * => Add customer if not exited
     */

    public ResponseEntity<?> deleteCustomer(Customer customerRequest) {
        try {
            if (customerRepo.findAllByPhoneNumberAndIsDeleteFalse(customerRequest.getPhoneNumber()).size() > 0) {
                customerRequest.setDelete(true);
                return updateCustomer(customerRequest);
            }
            return new ResponseEntity<>("Không tìm thấy customer với số điện thoại: " + customerRequest.getPhoneNumber(), HttpStatus.EXPECTATION_FAILED);
        } catch (Exception e) {
            log.info("Delete Customer fail: " + e);
            return new ResponseEntity<>("Có lỗi trong quá trình xóa khách hàng ! \nDetail: " + e, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * @param customerRequest
     * @return update or return error if not found or exception
     * @apiNote API update customer:
     * find customer by phone number => update customer
     */
    public ResponseEntity<?> updateCustomer(Customer customerRequest) {
        try {
            List<Customer> customerList = customerRepo.findAllByPhoneNumberAndIsDeleteFalse(customerRequest.getPhoneNumber());
            if (customerList.size() > 0) {
                customerRepo.delete(customerList.get(0));
                customerRepo.save(customerRequest);
                log.info("update Customer: Success !");
                return new ResponseEntity<>("Success !", HttpStatus.OK);
            }
            log.info("update Customer: Not found customer with phone number: " + customerRequest.getPhoneNumber());
            return new ResponseEntity<>("Không tìm thấy khách hàng với số điện thoại " + customerRequest.getPhoneNumber(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.info(e);
            return new ResponseEntity<>("Có lỗi trong quá trình thêm khách hàng ! \nDetail: " + e, HttpStatus.EXPECTATION_FAILED);
        }
    }


    /**
     * @param customerRequest
     * @return list customer if success or error if exception
     */
    public ResponseEntity<?> findCustomersByName(CustomerRequest customerRequest) {
        try {
            log.info("Find all customer by name data: " + customerRepo.findAllByCustomerNameContainsAndIsDeleteFalse(customerRequest.getCustomerName()));
            return new ResponseEntity<>(customerRepo.findAllByCustomerNameContainsAndIsDeleteFalse(customerRequest.getCustomerName()), HttpStatus.OK);
        } catch (Exception e) {
            log.info("Find all customer by name error: " + e);
            return new ResponseEntity<>("Lỗi trong quá trình tìm kiếm khách hàng !\nDetail: " + e, HttpStatus.EXPECTATION_FAILED);
        }
    }


    public ResponseEntity<?> findCustomersByPhone(CustomerRequest request) {
        try {
            return new ResponseEntity<>(customerRepo.findAllByIsDeleteFalse(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình lấy list khách hàng", HttpStatus.OK);
        }
    }
}
