package thinhld.ldt.roomservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thinhld.ldt.roomservice.model.Bed;

import java.util.List;

@Repository
public interface BedRepo extends JpaRepository<Bed, Long> {
    
    List<Bed> findAllByIsDeleteFalse();

    Bed findByIdIs(Long id);

}
