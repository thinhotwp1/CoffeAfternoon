package thinhld.ldt.reportservice.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import thinhld.ldt.reportservice.model.dto.MonthReport;
import thinhld.ldt.reportservice.model.dto.QuarterReport;
import thinhld.ldt.reportservice.model.dto.RequestReport;
import thinhld.ldt.reportservice.model.dto.ResponseReport;
import thinhld.ldt.reportservice.rest.RestClient;

@Log4j2
@Service
public class ScheduledReport {
    @Autowired
    private RestClient restClient;

    private final String getReportTicket = "http://localhost:8084/ticket/get-report-ticket";

    @Scheduled(cron = "0 0 2 1 * ?")
    public void runApiMonthly() {
        // Lấy báo cáo một lần 1 tháng vào lúc 2h sáng ngày mùng 1
        try {
            restClient.setUrl(getReportTicket);
            RequestReport requestReport = new RequestReport();
            requestReport.setTypeReport(MonthReport.MONTH_REPORT.getMoth());

            ResponseReport responseReport = restClient.postObject(requestReport, ResponseReport.class);
            MonthReport.MONTH_REPORT.setDetails(responseReport.getDetails());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Error call api to ticket service: " + e.getMessage());
        }

    }

    @Scheduled(cron = "0 0 2 1 * ?")
    public void runApiQuarterly() {
        // Lấy báo cáo một lần 3 tháng vào lúc 2h sáng ngày mùng 1 mỗi 3 tháng
        restClient.setUrl(getReportTicket);
        RequestReport requestReport = new RequestReport();
        requestReport.setTypeReport(QuarterReport.QUARTER_REPORT.getMoth());

        ResponseReport responseReport = restClient.postObject(requestReport, ResponseReport.class);
        QuarterReport.QUARTER_REPORT.setDetails(responseReport.getDetails());

    }

}
