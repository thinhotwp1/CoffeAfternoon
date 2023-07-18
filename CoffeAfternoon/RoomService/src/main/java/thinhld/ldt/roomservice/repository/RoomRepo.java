package thinhld.ldt.roomservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thinhld.ldt.roomservice.model.Room;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room, String> {

    List<Room> findAllByPhoneNumberAndIsDeleteFalse(String phoneNumber);

    List<Room> findAllByIsDeleteFalse();

    List<Room> findAllByRoomNameContainsAndIsDeleteFalse(String name);

    List<Room> findAllByPhoneNumberContainsAndIsDeleteFalse(String phone);

}
