package thinhld.ldt.ticketservice.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


public enum QuarterReport {

    QUARTER_REPORT(3, "Quarter report");

    @Getter
    private int moth;

    @Getter
    private String nameReport;

    @Getter
    @Setter
    private Map<String, Long> details; // Map: type ticket/total money for that type ticket

    QuarterReport(int moth, String nameReport) {
        this.moth = moth;
        this.nameReport = nameReport;
        setDefaultData();
    }

    private void setDefaultData() {
        details.put("Vé ngày", (long) 30000000);
        details.put("Vé 1 tháng", (long) 48000000);
        details.put("Vé 2 tháng", (long) 60000000);
        details.put("Vé 3 tháng", (long) 90000000);
    }
}

