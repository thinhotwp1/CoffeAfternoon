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

import java.util.List;


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
            return new ResponseEntity<>("Xảy ra lỗi trong quá trình lấy list phòng", HttpStatus.OK);
        }
    }

    /**
     * @param roomRequest
     * @return success if added or error if error
     * @apiNote API add room:
     * Validate exited room
     * => Add room if not exited
     */
    public ResponseEntity<?> addRoom(RoomRequest roomRequest) {
        try {
            // validate room
            if (roomRepo.findAllByPhoneNumberAndIsDeleteFalse(roomRequest.getPhoneNumber()).size() > 0) {
                log.info("Add Room: Exited Room !");
                return new ResponseEntity<>("Đã tồn tại phòng đã đăng ký bằng số điện thoại này ! ", HttpStatus.ALREADY_REPORTED);
            }
            // add room
            roomRepo.save(roomRequest.convertDTO(roomRequest));
            log.info("Success Added Room: " + roomRequest);
            return new ResponseEntity<>("Thêm thành công phòng " + roomRequest.getRoomName() + " với số điện thoại " + roomRequest.getPhoneNumber(), HttpStatus.OK);

        } catch (Exception ignored) {
            log.info("Exception Added Room: " + ignored);
            return new ResponseEntity<>("Có lỗi trong quá trình thêm phòng ! \nDetail: " + ignored, HttpStatus.EXPECTATION_FAILED);
        }
    }


    /**
     * @param roomRequest
     * @return success if added or error if error
     * @apiNote API add room:
     * Validate exited room
     * => Add room if not exited
     */

    public ResponseEntity<?> deleteRoom(Room roomRequest) {
        try {
            if (roomRepo.findAllByPhoneNumberAndIsDeleteFalse(roomRequest.getPhoneNumber()).size() > 0) {
                roomRequest.setDelete(true);
                return updateRoom(roomRequest);
            }
            return new ResponseEntity<>("Không tìm thấy room với số điện thoại: " + roomRequest.getPhoneNumber(), HttpStatus.EXPECTATION_FAILED);
        } catch (Exception e) {
            log.info("Delete Room fail: " + e);
            return new ResponseEntity<>("Có lỗi trong quá trình xóa phòng ! \nDetail: " + e, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * @param roomRequest
     * @return update or return error if not found or exception
     * @apiNote API update room:
     * find room by phone number => update room
     */
    public ResponseEntity<?> updateRoom(Room roomRequest) {
        try {
            List<Room> roomList = roomRepo.findAllByPhoneNumberAndIsDeleteFalse(roomRequest.getPhoneNumber());
            if (roomList.size() > 0) {
                roomRepo.delete(roomList.get(0));
                roomRepo.save(roomRequest);
                log.info("update Room: Success !");
                return new ResponseEntity<>("Success !", HttpStatus.OK);
            }
            log.info("update Room: Not found room with phone number: " + roomRequest.getPhoneNumber());
            return new ResponseEntity<>("Không tìm thấy phòng với số điện thoại " + roomRequest.getPhoneNumber(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.info(e);
            return new ResponseEntity<>("Có lỗi trong quá trình thêm phòng ! \nDetail: " + e, HttpStatus.EXPECTATION_FAILED);
        }
    }


    /**
     * @param roomRequest
     * @return list room if success or error if exception
     */
    public ResponseEntity<?> findRoomsByName(RoomRequest roomRequest) {
        try {
            RoomResponse roomResponse = new RoomResponse();
            log.info("Find all room by name data: " + roomRepo.findAllByRoomNameContainsAndIsDeleteFalse(roomRequest.getRoomName()));
            return new ResponseEntity<>(roomResponse.convertDTO(roomRepo.findAllByRoomNameContainsAndIsDeleteFalse(roomRequest.getRoomName())), HttpStatus.OK);
        } catch (Exception e) {
            log.info("Find all room by name error: " + e);
            return new ResponseEntity<>("Lỗi trong quá trình tìm kiếm phòng !\nDetail: " + e, HttpStatus.EXPECTATION_FAILED);
        }
    }


    /**
     * @param roomRequest
     * @return list room if success or error if exception
     */
    public ResponseEntity<?> findRoomsByPhone(RoomRequest roomRequest) {
        try {
            RoomResponse roomResponse = new RoomResponse();
            log.info("Find all room by phone number data: " + roomRepo.findAllByPhoneNumberContainsAndIsDeleteFalse(roomRequest.getPhoneNumber()));
            return new ResponseEntity<>(roomResponse.convertDTO(roomRepo.findAllByPhoneNumberContainsAndIsDeleteFalse(roomRequest.getPhoneNumber())), HttpStatus.OK);
        } catch (Exception e) {
            log.info("Find all room by name error: " + e);
            return new ResponseEntity<>("Lỗi trong quá trình tìm kiếm phòng !\nDetail: " + e, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
