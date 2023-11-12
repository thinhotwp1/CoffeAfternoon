package thinhld.ldt.bedservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.modelmapper.ModelMapper;
import thinhld.ldt.bedservice.model.Room;

import java.util.Date;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomRequest {
    private String roomName;
    private String roomType;
    private boolean isActive = true;

    public Room convertDTO(RoomRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(request,Room.class);
    }
}