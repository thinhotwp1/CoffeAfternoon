package thinhld.ldt.customerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import thinhld.ldt.customerservice.log.SystemLog;
import thinhld.ldt.customerservice.log.TypeLog;
import thinhld.ldt.customerservice.model.Customer;
import thinhld.ldt.customerservice.model.CustomerRequest;
import thinhld.ldt.customerservice.model.CustomerResponse;
import thinhld.ldt.customerservice.repository.CustomerRepo;
import thinhld.ldt.customerservice.rest.RestClient;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service
public class CustomerService {


    @Autowired
    CustomerRepo customerRepo;

    /**
     * @return list Customer in database
     * @apiNote API get all customer
     */
    public ResponseEntity<?> getAllCustomer() {
        try {
            CustomerResponse customerResponse = new CustomerResponse();
            return new ResponseEntity<>(customerResponse.convertDTO(customerRepo.findCustomersByOrderByCreateDateDesc()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
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
            if(customerRequest.getPhoneNumber().length()!=10){
                return new ResponseEntity<>("Số điện thoại không hợp lệ !", HttpStatus.OK);
            }
            // validate customer
            if (!customerRepo.findCustomersByPhoneNumber(customerRequest.getPhoneNumber()).isEmpty()) {
                SystemLog.log("Add Customer - Exited Customer with phone number:" + customerRequest.getPhoneNumber(), TypeLog.INFO);
                return new ResponseEntity<>("Đã tồn tại khách hàng đã đăng ký bằng số điện thoại " + customerRequest.getPhoneNumber(), HttpStatus.BAD_REQUEST);
            }

            customerRepo.saveAndFlush(customerRequest.convertDTO(customerRequest));

            return new ResponseEntity<>("Thêm thành công khách hàng " + customerRequest.getCustomerName() + " với số điện thoại " + customerRequest.getPhoneNumber(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Có lỗi trong quá trình thêm khách hàng ! \nDetail: " + e, HttpStatus.EXPECTATION_FAILED);
        }
    }



    /**
     * @param customerRequest
     * @return string success if deleted or error if error
     * @apiNote API add customer:
     * set delete is true and set bed id is null
     * => Delete customer if not exited
     */

    public ResponseEntity<?> deleteCustomer(Customer customerRequest) {
        try {
            if(customerRepo.findById(customerRequest.getPhoneNumber()).isPresent()){
                customerRepo.deleteById(customerRequest.getPhoneNumber());
                return new ResponseEntity<>("Xóa thành công khách hàng có số điện thoại: " + customerRequest.getPhoneNumber(), HttpStatus.EXPECTATION_FAILED);
            }else {
                return new ResponseEntity<>("Không tìm thấy khách hàng với số điện thoại: " + customerRequest.getPhoneNumber(), HttpStatus.EXPECTATION_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            List<Customer> customerList = customerRepo.findCustomersByPhoneNumber(customerRequest.getPhoneNumber());
            if (!customerList.isEmpty()) {
                customerRepo.delete(customerList.get(0));
                customerRepo.save(customerRequest);
                SystemLog.log("update Customer: Success !",TypeLog.INFO);
                return new ResponseEntity<>("Success !", HttpStatus.OK);
            }
            SystemLog.log("update Customer: Not found customer with phone number: " + customerRequest.getPhoneNumber(),TypeLog.INFO);
            return new ResponseEntity<>("Không tìm thấy khách hàng với số điện thoại " + customerRequest.getPhoneNumber(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Có lỗi trong quá trình thêm khách hàng ! \nDetail: " + e, HttpStatus.EXPECTATION_FAILED);
        }
    }


    /**
     * @param customerRequest
     * @return list customer if success or error if exception
     */
    public ResponseEntity<?> findCustomersByPhoneOrName(CustomerRequest customerRequest) {
        try {
            CustomerResponse customerResponse = new CustomerResponse();
            return new ResponseEntity<>(customerResponse.convertDTO(customerRepo.findCustomersByPhoneNumberContainsAndCustomerNameContains(customerRequest.getPhoneNumber(),customerRequest.getCustomerName())), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Lỗi trong quá trình tìm kiếm khách hàng !\nDetail: " + e, HttpStatus.EXPECTATION_FAILED);
        }
    }

//    void sendRabbit(TicketMessage message) {
//        message.setTopic("Customer service");
//        rabbitTemplate.convertAndSend(customer_exchange.getName(), ROUTING_CUSTOMER, message);
//    }

}
