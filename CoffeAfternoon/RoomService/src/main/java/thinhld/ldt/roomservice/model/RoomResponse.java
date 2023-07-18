package thinhld.ldt.roomservice.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoomResponse {
    private String phoneNumber;
    private String roomName;
    private int type;
    private boolean isDelete;
    private String lastUpdate;
    private String userCurrent;
    private String lastComing;

    public RoomResponse() {
    }

    public RoomResponse(String phoneNumber, String roomName, int type , String lastUpdate, String userCurrent, String lastComing) {
        this.phoneNumber = phoneNumber;
        this.roomName = roomName;
        this.type = type;
        this.lastUpdate = lastUpdate;
        this.userCurrent = userCurrent;
        this.lastComing = lastComing;
    }

    public List<RoomResponse> convertDTO(List<Room> rooms) throws Exception{
        List<RoomResponse> responses = new ArrayList<>();

        for (Room room : rooms) {
            responses.add(new RoomResponse(room.getPhoneNumber(), room.getRoomName(), room.getType(), room.getLastUpdate(), room.getUserCurrent(), room.getLastComing()));
        }
        return responses;
    }
}
