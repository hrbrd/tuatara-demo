package pl.tuatara.demo.service;

import org.springframework.stereotype.Service;
import pl.tuatara.demo.dao.CompanyRepository;
import pl.tuatara.demo.model.dto.CompanyDto;
import pl.tuatara.demo.model.entity.Company;
import pl.tuatara.demo.model.exception.CompanyAlreadyExistsException;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void create(CompanyDto companyDto) throws CompanyAlreadyExistsException {
        if(companyRepository.existsById(companyDto.getName()))
            throw new CompanyAlreadyExistsException();
        companyRepository.save(convertToCompany(companyDto));
    }

    private Company convertToCompany(CompanyDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setLatitude(companyDto.getLatitude());
        company.setLongitude(companyDto.getLongitude());

        return company;
    }

}