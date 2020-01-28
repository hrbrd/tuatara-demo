package pl.tuatara.demo.service;

import pl.tuatara.demo.model.dto.CompanyDto;

import java.util.List;

public interface ICompanyService {
    CompanyDto get(String name);
    void create(CompanyDto companyDto) throws Exception;
    void assignUser(String name, String username);
    List<CompanyDto> getAll();
    void delete(String name);
}
