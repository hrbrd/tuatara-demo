package pl.tuatara.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tuatara.demo.model.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
