package thinhld.ldt.roomservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.modelmapper.ModelMapper;
import thinhld.ldt.roomservice.conmon.config.UserConfig;

import java.util.Date;


@Data
public class RoomRequest {    
    private long id;
    private String roomName;
    private int bedNumber;
    private boolean isActive;

    public Room convertDTO(RoomRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        Room room = modelMapper.map(request,Room.class);
        Date date = new Date();
        Long currentTime = date.getTime();
        room.setLastUpdate(currentTime.toString());
        room.setUserCurrent(UserConfig.userNameCurrent);
        return room;
    }
}