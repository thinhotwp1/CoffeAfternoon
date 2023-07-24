package thinhld.ldt.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thinhld.ldt.ticketservice.model.Ticket;

import java.util.Date;
import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, String> {

    List<Ticket> findAllByIsDeleteFalse();


}
