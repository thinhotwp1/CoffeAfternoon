package thinhld.ldt.bedservice.model;

import lombok.Data;
import org.modelmapper.ModelMapper;
import thinhld.ldt.bedservice.conmon.config.UserConfig;

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