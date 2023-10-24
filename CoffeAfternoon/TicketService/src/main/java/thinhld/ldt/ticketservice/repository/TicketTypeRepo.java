package thinhld.ldt.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thinhld.ldt.ticketservice.model.Ticket;
import thinhld.ldt.ticketservice.model.TicketType;

@Repository
public interface TicketTypeRepo extends JpaRepository<TicketType, String> {

}
