package thinhld.ldt.bedservice.model.dto;

import lombok.Data;
import org.modelmapper.ModelMapper;
import thinhld.ldt.bedservice.model.Bed;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Data
public class BedResponse {
    private long id;
    private long roomId;
    private String bedName;
    private String customerId;
    private Calendar dateTicket;
    private boolean needFix;
    private boolean isFixing;

    public BedResponse() {
    }


    public List<BedResponse> convertDTO(List<Bed> beds) throws Exception{
        List<BedResponse> responses = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Bed bed : beds) {
            responses.add(modelMapper.map(bed,BedResponse.class));
        }
        return responses;
    }
}
