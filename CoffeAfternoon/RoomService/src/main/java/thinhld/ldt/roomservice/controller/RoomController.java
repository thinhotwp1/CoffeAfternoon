package thinhld.ldt.roomservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thinhld.ldt.roomservice.model.RoomRequest;
import thinhld.ldt.roomservice.service.RoomService;

@RestController
@RequestMapping("/room")
@Log4j2
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @GetMapping("/test")
    public String testAPI() {
        return "Success";
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getListRoom() {
        return roomService.getAllRoom();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRoom(@RequestBody RoomRequest roomRequest) {
        return roomService.addRoom(roomRequest);
    }


}
