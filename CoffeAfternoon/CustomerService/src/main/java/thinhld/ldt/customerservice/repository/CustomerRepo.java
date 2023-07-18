package thinhld.ldt.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thinhld.ldt.customerservice.model.Customer;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {

    List<Customer> findAllByPhoneNumberAndIsDeleteFalse(String phoneNumber);

    List<Customer> findAllByIsDeleteFalse();

    List<Customer> findAllByCustomerNameContainsAndIsDeleteFalse(String name);

}
