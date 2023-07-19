package thinhld.ldt.roomservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thinhld.ldt.roomservice.model.Room;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

    List<Room> findAllByIsDeleteFalse();

}
