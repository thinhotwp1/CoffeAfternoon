package thinhld.ldt.bedservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thinhld.ldt.bedservice.model.Room;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {


}
