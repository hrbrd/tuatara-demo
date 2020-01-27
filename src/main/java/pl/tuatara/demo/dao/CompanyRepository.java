package pl.tuatara.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tuatara.demo.model.entity.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, String> {
}
