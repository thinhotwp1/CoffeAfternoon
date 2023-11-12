package thinhld.ldt.bedservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thinhld.ldt.bedservice.model.dto.BedRequest;
import thinhld.ldt.bedservice.model.dto.HireBed;
import thinhld.ldt.bedservice.service.BedService;
import thinhld.ldt.bedservice.model.*;

@RestController
@RequestMapping("/bed")
@CrossOrigin
public class BedController {

    @Autowired
    private BedService bedService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllBed() {
        return bedService.getAllBed();
    }

    @PostMapping("/hire")
    public ResponseEntity<?> hireBed(@RequestBody Bed bedRequest) {
        return bedService.hireBed(bedRequest);
    }
    @PostMapping("/hire-month")
    public ResponseEntity<?> hireBedMonth(@RequestBody HireBed request) {
        return bedService.hireBedMonth(request);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addBed(@RequestBody BedRequest bedRequest) {
        return bedService.addBed(bedRequest);
    }


}
