package thinhld.ldt.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thinhld.ldt.userservice.model.User;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findUserByUserName(String user);

    List<User> findAllByUserName(String user);


}
