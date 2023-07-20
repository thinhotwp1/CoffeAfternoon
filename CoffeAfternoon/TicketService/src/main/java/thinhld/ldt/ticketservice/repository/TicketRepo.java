package thinhld.ldt.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thinhld.ldt.ticketservice.model.Ticket;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, String> {

    List<Ticket> findAllByPhoneNumberAndIsDeleteFalse(String phoneNumber);

    List<Ticket> findAllByPhoneNumberAndIsDeleteTrue(String phoneNumber);

    List<Ticket> findAllByIsDeleteFalse();

    List<Ticket> findAllByIsDeleteTrue();

    List<Ticket> findAllByTicketNameContainsAndIsDeleteFalse(String name);

    List<Ticket> findAllByPhoneNumberContainsAndIsDeleteFalse(String phone);

    List<Ticket> findAllByPhoneNumberContainsAndIsDeleteTrue(String phone);
    List<Ticket> findAllByTicketNameContainsAndIsDeleteTrue(String phone);

}
