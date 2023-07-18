package thinhld.ldt.roomservice.model;

import lombok.Data;
import thinhld.ldt.roomservice.controller.RoomController;

import java.util.Date;


@Data
public class RoomRequest {    
    private String phoneNumber;
    private String roomName;
    private int type;
    private boolean isDelete;

    public Room convertDTO(RoomRequest request) {
        Room room = new Room();
        room.setRoomName(request.getRoomName());
        room.setType(request.getType());
        room.setPhoneNumber(request.getPhoneNumber());
        Date date = new Date();
        Long currentTime = date.getTime();
        room.setLastUpdate(currentTime.toString());
        room.setUserCurrent(RoomController.userNameCurrent);
        return room;
    }
}