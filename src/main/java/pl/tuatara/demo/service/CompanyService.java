package pl.tuatara.demo.service;

import org.springframework.stereotype.Service;
import pl.tuatara.demo.dao.CompanyRepository;
import pl.tuatara.demo.dao.UserRepository;
import pl.tuatara.demo.model.dto.CompanyDto;
import pl.tuatara.demo.model.entity.Company;
import pl.tuatara.demo.model.entity.User;
import pl.tuatara.demo.model.exception.CompanyAlreadyExistsException;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;
    private UserRepository userRepository;

    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public void create(CompanyDto companyDto) throws CompanyAlreadyExistsException {
        if(companyRepository.existsById(companyDto.getName()))
            throw new CompanyAlreadyExistsException();
        companyRepository.save(convertToCompany(companyDto));
    }

//    TODO exceptions
    public void assignUser(String name, String username) {
        Company company = companyRepository.findById(name).get();
        User user = userRepository.findById(username).get();
        company.getUsers().add(user);
        companyRepository.save(company);
    }

    private Company convertToCompany(CompanyDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setLatitude(companyDto.getLatitude());
        company.setLongitude(companyDto.getLongitude());

        return company;
    }

}