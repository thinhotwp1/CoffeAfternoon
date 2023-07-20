package thinhld.ldt.roomservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thinhld.ldt.roomservice.service.BedService;
import thinhld.ldt.roomservice.model.*;

@RestController
@RequestMapping("/bed")
public class BedController {

    @Autowired
    private BedService bedService;

    @GetMapping("/test")
    public String testAPI() {
        return "Success";
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getListRoom() {
        return bedService.getAllBed();
    }

    @PostMapping("/hire")
    public ResponseEntity<?> hireRoom(@RequestBody Bed bedRequest) {
        return bedService.hireRoom(bedRequest);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRoom(@RequestBody BedRequest bedRequest) {
        return bedService.addRoom(bedRequest);
    }


}
