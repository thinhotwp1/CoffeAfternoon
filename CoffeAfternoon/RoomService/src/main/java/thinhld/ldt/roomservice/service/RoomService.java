package thinhld.ldt.roomservice.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import thinhld.ldt.roomservice.model.Room;
import thinhld.ldt.roomservice.model.RoomRequest;
import thinhld.ldt.roomservice.model.RoomResponse;
import thinhld.ldt.roomservice.repository.RoomRepo;


@Service
@Log4j2(topic = "RoomService")
public class RoomService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    RoomRepo roomRepo;

    /**
     * @return list Room in database
     * @apiNote API get all room:
     */
    public ResponseEntity<?> getAllRoom() {
        try {
            RoomResponse roomResponse = new RoomResponse();
            return new ResponseEntity<>(roomResponse.convertDTO(roomRepo.findAllByIsDeleteFalse()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình lấy danh sách phòng !", HttpStatus.OK);
        }
    }

    public ResponseEntity<?> addRoom(RoomRequest roomRequest) {
        try {
            Room room = roomRequest.convertDTO(roomRequest);
            room.setActive(true);
            roomRepo.save(room);
            return new ResponseEntity<>("Thêm thành công " + roomRequest.getRoomName(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình thêm " + roomRequest.getRoomName(), HttpStatus.OK);
        }
    }
}
