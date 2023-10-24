package thinhld.ldt.reportservice.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


public enum MonthReport {

    MONTH_REPORT(1, "Month report");

    @Getter
    private int moth;

    @Getter
    private String nameReport;

    @Getter
    @Setter
    private Map<String, Long> details; // Map: type ticket/total money for that type ticket

    MonthReport(int moth, String nameReport) {
        this.moth = moth;
        this.nameReport = nameReport;
        setDefaultData();
    }

    private void setDefaultData() {
        details = new HashMap<>();
        details.put("Vé ngày", (long) 10000000);
        details.put("Vé 1 tháng", (long) 18000000);
        details.put("Vé 2 tháng", (long) 20000000);
        details.put("Vé 3 tháng", (long) 30000000);
    }
}

