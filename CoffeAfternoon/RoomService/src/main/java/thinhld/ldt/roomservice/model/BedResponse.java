package thinhld.ldt.roomservice.model;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Data
public class BedResponse {
    private String phoneNumber;
    private String roomName;
    private int type;

    public BedResponse() {
    }


    public List<BedResponse> convertDTO(List<Bed> rooms) throws Exception{
        List<BedResponse> responses = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Bed room : rooms) {
            responses.add(modelMapper.map(room,BedResponse.class));
        }
        return responses;
    }
}
