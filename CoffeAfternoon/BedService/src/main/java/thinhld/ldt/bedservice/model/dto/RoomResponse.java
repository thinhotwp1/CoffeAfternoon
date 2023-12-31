package thinhld.ldt.bedservice.model.dto;

import lombok.Data;
import org.modelmapper.ModelMapper;
import thinhld.ldt.bedservice.model.Bed;
import thinhld.ldt.bedservice.model.Room;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoomResponse {
    private long id;
    private String roomName;
    private String roomType;
    private boolean isActive;
    private List<Bed> beds;

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
