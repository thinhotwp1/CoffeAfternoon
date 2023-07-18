package thinhld.ldt.roomservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thinhld.ldt.roomservice.conmon.Message;
import thinhld.ldt.roomservice.model.Room;
import thinhld.ldt.roomservice.model.RoomRequest;
import thinhld.ldt.roomservice.service.RoomService;

@RestController
@RequestMapping("/room")
@Log4j2
public class RoomController {
    public static String userNameCurrent = "";
    public static int role = 0;

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @RabbitListener(queues = "queue.user")
    private void receiveFromA(Message message) {
        userNameCurrent = message.getUser();
        role = message.getRole();
        log.info("User current: " + userNameCurrent);
    }

    @GetMapping("/test")
    public String testAPI() {
        return "Success";
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getListRoom() {
        return roomService.getAllRoom();
    }

    @PostMapping("/find-by-name")
    public ResponseEntity<?> findRoomsByName(@RequestBody RoomRequest request) {
        return roomService.findRoomsByName(request);
    }

    @PostMapping("/find-by-phone")
    public ResponseEntity<?> findRoomsByPhone(@RequestBody RoomRequest request) {
        return roomService.findRoomsByPhone(request);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRoom(@RequestBody RoomRequest request) {
        return roomService.addRoom(request);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteRoom(@RequestBody Room request) {
        return roomService.deleteRoom(request);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateRoom(@RequestBody Room request) {
        return roomService.updateRoom(request);
    }
}
