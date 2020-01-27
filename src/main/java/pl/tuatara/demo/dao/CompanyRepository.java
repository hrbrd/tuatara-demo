package pl.tuatara.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tuatara.demo.model.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
}
