package pl.tuatara.demo.converter;

import org.springframework.stereotype.Component;
import pl.tuatara.demo.model.dto.CompanyDto;
import pl.tuatara.demo.model.entity.Company;

@Component
public class CompanyConverter {

    public Company convertToCompany(CompanyDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setStreet(companyDto.getStreet());
        company.setCity(companyDto.getCity());
        company.setCountry(companyDto.getCountry());
        company.setLatitude(companyDto.getLatitude());
        company.setLongitude(companyDto.getLongitude());

        return company;
    }

    public CompanyDto convertToDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName(company.getName());
        companyDto.setStreet(company.getStreet());
        companyDto.setCity(company.getCity());
        companyDto.setCountry(company.getCountry());
        companyDto.setLatitude(company.getLatitude());
        companyDto.setLongitude(company.getLongitude());
        companyDto.setUsers(company.getUsers());

        return companyDto;
    }

}