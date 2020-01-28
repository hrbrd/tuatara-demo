package pl.tuatara.demo.service;

import pl.tuatara.demo.model.dto.CompanyDto;
import pl.tuatara.demo.model.exception.CompanyNotFoundException;
import pl.tuatara.demo.model.exception.UserAlreadyAssignedException;
import pl.tuatara.demo.model.exception.UserNotFoundException;

import java.util.List;

public interface ICompanyService {
    CompanyDto get(String companyName) throws CompanyNotFoundException;
    void create(CompanyDto companyDto) throws Exception;
    void assignUser(String companyName, String username) throws CompanyNotFoundException, UserNotFoundException, UserAlreadyAssignedException;
    List<CompanyDto> getAll();
    void delete(String companyName) throws CompanyNotFoundException;
}
