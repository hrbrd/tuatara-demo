package pl.tuatara.demo.service.impl;

import org.springframework.stereotype.Service;
import pl.tuatara.demo.converter.CompanyConverter;
import pl.tuatara.demo.dao.CompanyRepository;
import pl.tuatara.demo.dao.UserRepository;
import pl.tuatara.demo.model.Location;
import pl.tuatara.demo.model.dto.CompanyDto;
import pl.tuatara.demo.model.entity.Company;
import pl.tuatara.demo.model.entity.User;
import pl.tuatara.demo.model.exception.CompanyAlreadyExistsException;
import pl.tuatara.demo.model.exception.CompanyNotFoundException;
import pl.tuatara.demo.model.exception.UserNotFoundException;
import pl.tuatara.demo.service.ICompanyService;
import pl.tuatara.demo.service.ILocationFetchingService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService implements ICompanyService {

    private CompanyRepository companyRepository;
    private UserRepository userRepository;
    private ILocationFetchingService locationFetchingService;
    private CompanyConverter companyConverter;

    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository,
                          ILocationFetchingService locationFetchingService, CompanyConverter companyConverter) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.locationFetchingService = locationFetchingService;
        this.companyConverter = companyConverter;
    }

    @Override
    public CompanyDto get(String name) throws CompanyNotFoundException {
        return companyConverter.convertToDto(companyRepository.findById(name).orElseThrow(() -> new CompanyNotFoundException()));
    }

    @Override
    public void create(CompanyDto companyDto) throws Exception {
        if (companyRepository.existsById(companyDto.getName()))
            throw new CompanyAlreadyExistsException();
        Location location = locationFetchingService.fetchLocation(companyDto);
        Company company = companyConverter.convertToCompany(companyDto);
        company.setLatitude(location.getLatitude());
        company.setLongitude(location.getLongitude());
        companyRepository.save(company);
    }

    @Override
    public void assignUser(String name, String username) throws CompanyNotFoundException, UserNotFoundException {
        Company company = companyRepository.findById(name).orElseThrow(() -> new CompanyNotFoundException());
        User user = userRepository.findById(username).orElseThrow(() -> new UserNotFoundException());;
        company.getUsers().add(user);
        companyRepository.save(company);
    }

    @Override
    public List<CompanyDto> getAll() {
        return companyRepository.findAll()
                .stream().map(company -> {
                    CompanyDto companyDto = companyConverter.convertToDto(company);
                    companyDto.setUsers(null);
                    return companyDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String name) throws CompanyNotFoundException {
        companyRepository.findById(name).orElseThrow(() -> new CompanyNotFoundException());
        companyRepository.deleteById(name);
    }

}