package thinhld.ldt.ticketservice.model.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.modelmapper.ModelMapper;
import thinhld.ldt.ticketservice.model.Ticket;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Data
public class TicketRequest {
    private long idTypeTicket;
    private String phoneNumber;
    private String bedId;
    private int dateTicket;
    private long price;

    public Ticket convertDTO(TicketRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        Ticket ticket = modelMapper.map(request, Ticket.class);

        Date currentTime = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime);
        // Cộng thêm số giờ cho vé ngày
        calendar.add(Calendar.DAY_OF_MONTH, request.getDateTicket());
        ticket.setDateTicket(calendar);

        return ticket;
    }
}
