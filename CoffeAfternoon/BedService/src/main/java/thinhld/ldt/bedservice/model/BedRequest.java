package thinhld.ldt.bedservice.model;

import lombok.Data;
import org.modelmapper.ModelMapper;
import thinhld.ldt.bedservice.conmon.config.UserConfig;

import java.util.Date;


@Data
public class BedRequest {
    private long roomId;
    private String bedName;
    private boolean needFix;
    private boolean isUsing;
    private boolean isFixing;

    public Bed convertDTO(BedRequest request) {
        Date date = new Date();
        ModelMapper modelMapper = new ModelMapper();
        Bed bed = modelMapper.map(request,Bed.class);
        Long currentTime = date.getTime();
        bed.setLastUpdate(currentTime.toString());
        bed.setUserCurrent(UserConfig.userNameCurrent);
        return bed;
    }
}