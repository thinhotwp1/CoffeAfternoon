package thinhld.ldt.bedservice.model.dto;

import lombok.Data;
import org.modelmapper.ModelMapper;
import thinhld.ldt.bedservice.model.Bed;


@Data
public class BedRequest {
    private long roomId;
    private String bedName;
    private boolean needFix = false;
    private boolean isFixing = false;

    public Bed convertDTO(BedRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        Bed bed = modelMapper.map(request,Bed.class);
        bed.setId(-1L);
        return bed;
    }
}