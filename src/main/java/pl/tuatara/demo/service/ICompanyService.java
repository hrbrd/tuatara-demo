package pl.tuatara.demo.service;

import pl.tuatara.demo.model.dto.CompanyDto;
import pl.tuatara.demo.model.exception.CompanyNotFoundException;
import pl.tuatara.demo.model.exception.UserNotFoundException;

import java.util.List;

public interface ICompanyService {
    CompanyDto get(String name) throws CompanyNotFoundException;
    void create(CompanyDto companyDto) throws Exception;
    void assignUser(String name, String username) throws CompanyNotFoundException, UserNotFoundException;
    List<CompanyDto> getAll();
    void delete(String name) throws CompanyNotFoundException;
}
