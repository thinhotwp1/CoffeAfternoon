package thinhld.ldt.bedservice.model.dto;

import lombok.Data;
import thinhld.ldt.bedservice.model.Bed;

import java.util.List;

@Data
public class ListBedHire {
    private List<Bed> listActive;
    private List<Bed> listUsed;
}
