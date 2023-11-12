package thinhld.ldt.bedservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thinhld.ldt.bedservice.model.Room;
import thinhld.ldt.bedservice.model.dto.RoomRequest;
import thinhld.ldt.bedservice.service.RoomService;

@RestController
@CrossOrigin
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getListRoom() {
        return roomService.getAllRoom();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRoom(@RequestBody RoomRequest roomRequest) {
        return roomService.addRoom(roomRequest);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateRoom(@RequestBody Room roomRequest) {
        return roomService.updateRoom(roomRequest);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteRoom(@RequestBody Room roomRequest) {
        return roomService.deleteRoom(roomRequest);
    }

}
