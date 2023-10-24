package thinhld.ldt.reportservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import thinhld.ldt.reportservice.log.SystemLog;
import thinhld.ldt.reportservice.log.TypeLog;
import thinhld.ldt.reportservice.model.Report;
import thinhld.ldt.reportservice.repository.ReportRepo;
import thinhld.ldt.reportservice.rest.RestClient;

import java.util.Calendar;
import java.util.Date;
import java.util.List;



@Service
public class ReportService {


    @Autowired
    ReportRepo reportRepo;

    @Autowired
    private RestClient restClient;

    private final String urlAddTicket = "localhost:8081/add-ticket";



}
