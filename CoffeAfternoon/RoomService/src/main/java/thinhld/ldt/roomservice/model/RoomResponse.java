package thinhld.ldt.roomservice.model;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoomResponse {
    private long id;
    private String roomName;
    private int bedNumber;
    private boolean isActive = true;
    private List<Bed> beds;

    private boolean isDelete;
    private String lastUpdate;
    private String userCurrent;

    public RoomResponse() {
    }

    public List<RoomResponse> convertDTO(List<Room> rooms) throws Exception{
        List<RoomResponse> responses = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Room room : rooms) {
            responses.add(modelMapper.map(room,RoomResponse.class));
        }
        return responses;
    }
}
