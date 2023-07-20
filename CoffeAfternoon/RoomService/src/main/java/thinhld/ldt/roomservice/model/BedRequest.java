package thinhld.ldt.roomservice.model;

import lombok.Data;
import thinhld.ldt.roomservice.conmon.config.UserConfig;

import java.util.Date;


@Data
public class BedRequest {
    private String id;
    private String bed_id;
    private int bedName;
    private boolean needFix;
    private boolean isUsing;
    private boolean isFixing;

    public Bed convertDTO(BedRequest request) {
        Bed bed = new Bed();
        Date date = new Date();
        Long currentTime = date.getTime();
        bed.setLastUpdate(currentTime.toString());
        bed.setUserCurrent(UserConfig.userNameCurrent);
        return bed;
    }
}