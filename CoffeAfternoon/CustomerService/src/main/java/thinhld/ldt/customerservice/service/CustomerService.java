package thinhld.ldt.customerservice.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import thinhld.ldt.customerservice.conmon.model.Message;
import thinhld.ldt.customerservice.conmon.model.TicketMessage;
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

import static thinhld.ldt.customerservice.config.RabbitMQConfiguration.ROUTING_CUSTOMER;


@Service
public class CustomerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange customer_exchange;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    private RestClient restClient;

    private final String urlAddTicket = "localhost:8081/add-ticket";

    /**
     * @return list Customer in database
     * @apiNote API get all customer
     */
    public ResponseEntity<?> getAllCustomer() {
        try {
            CustomerResponse customerResponse = new CustomerResponse();
            return new ResponseEntity<>(customerResponse.convertDTO(customerRepo.findAll()), HttpStatus.OK);
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
            if (!customerRepo.findCustomersByPhoneNumberContains(customerRequest.getPhoneNumber()).isEmpty()) {
                SystemLog.log("Add Customer - Exited Customer with phone number:" + customerRequest.getPhoneNumber(), TypeLog.INFO);
                return new ResponseEntity<>("Đã tồn tại khách hàng đã đăng ký bằng số điện thoại " + customerRequest.getPhoneNumber(), HttpStatus.BAD_REQUEST);
            }
            // Add customer

            // Lấy thời gian hiện tại
            Date currentTime = new Date();
            // Tạo một đối tượng Calendar và thiết lập thời gian là currentTime
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentTime);
            // Cộng thêm số tháng theo loại vé vào Calendar
            calendar.add(Calendar.MONTH, customerRequest.getTypeTicket());
            Customer customer = customerRequest.convertDTO(customerRequest);
//            customer.setDateTicket(calendar);

            customerRepo.save(customer);
            SystemLog.log("Success Added Customer: " + customerRequest,TypeLog.INFO);

            //Map object
            ModelMapper modelMapper = new ModelMapper();
            TicketMessage ticketMessage = modelMapper.map(customer, TicketMessage.class);
            ticketMessage.setDateTicket(calendar);

            //Send rabbit
            sendRabbit(ticketMessage);

            // Rest API
            restClient.setUrl(urlAddTicket);
            restClient.postObject(ticketMessage, Object.class);
            SystemLog.log("Send message to ticket service, data: " + ticketMessage,TypeLog.INFO);

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
            List<Customer> customerList = customerRepo.findCustomersByPhoneNumberContains(customerRequest.getPhoneNumber());
            if (!customerList.isEmpty()) {
                return new ResponseEntity<>("Không thể xóa khách hàng này vì vé tháng đang còn hạn sử dụng !", HttpStatus.EXPECTATION_FAILED);
            }
            return new ResponseEntity<>("Không tìm thấy khách hàng với số điện thoại: " + customerRequest.getPhoneNumber(), HttpStatus.EXPECTATION_FAILED);
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
            List<Customer> customerList = customerRepo.findCustomersByPhoneNumberContains(customerRequest.getPhoneNumber());
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
    public ResponseEntity<?> findCustomersByName(CustomerRequest customerRequest) {
        try {
            CustomerResponse customerResponse = new CustomerResponse();
            return new ResponseEntity<>(customerResponse.convertDTO(customerRepo.findCustomersByCustomerNameContains(customerRequest.getCustomerName())), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Lỗi trong quá trình tìm kiếm khách hàng !\nDetail: " + e, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * @param customerRequest
     * @return list customer if success or error if exception
     */
    public ResponseEntity<?> findCustomersByPhone(CustomerRequest customerRequest) {
        try {
            CustomerResponse customerResponse = new CustomerResponse();
            return new ResponseEntity<>(customerResponse.convertDTO(customerRepo.findCustomersByPhoneNumberContains(customerRequest.getPhoneNumber())), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Lỗi trong quá trình tìm kiếm khách hàng !\nDetail: " + e, HttpStatus.EXPECTATION_FAILED);
        }
    }

    void sendRabbit(TicketMessage message) {
        message.setTopic("Customer service");
        rabbitTemplate.convertAndSend(customer_exchange.getName(), ROUTING_CUSTOMER, message);
    }

}
