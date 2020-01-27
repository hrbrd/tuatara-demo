package pl.tuatara.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tuatara.demo.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
