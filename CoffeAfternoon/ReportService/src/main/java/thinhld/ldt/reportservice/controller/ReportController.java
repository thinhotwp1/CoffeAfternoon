package thinhld.ldt.reportservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thinhld.ldt.reportservice.model.Report;
import thinhld.ldt.reportservice.model.dto.MonthReport;
import thinhld.ldt.reportservice.model.dto.ResponseReport;
import thinhld.ldt.reportservice.service.ReportService;
import thinhld.ldt.reportservice.service.ScheduledReport;

import java.util.Map;


@RestController
@RequestMapping("/report")
@Log4j2
@CrossOrigin
public class ReportController {
    @Autowired
    ReportService reportService;
    @Autowired
    ScheduledReport scheduledReport;

    @GetMapping("")
    public ResponseReport getReport(){
        scheduledReport.runApiMonthly();

        ResponseReport responseReport = new ResponseReport();
        responseReport.setTypeReport(MonthReport.MONTH_REPORT.getMoth());
        responseReport.setDetails(MonthReport.MONTH_REPORT.getDetails());
        return responseReport;
    }

}
