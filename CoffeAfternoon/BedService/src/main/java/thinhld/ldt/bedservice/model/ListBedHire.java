package thinhld.ldt.bedservice.model;

import lombok.Data;

import java.util.List;

@Data
public class ListBedHire {
    private List<Bed> listActive;
    private List<Bed> listUsed;
}
