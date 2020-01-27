package pl.tuatara.demo.service;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;
import pl.tuatara.demo.converter.CompanyConverter;
import pl.tuatara.demo.dao.CompanyRepository;
import pl.tuatara.demo.dao.UserRepository;
import pl.tuatara.demo.model.dto.CompanyDto;
import pl.tuatara.demo.model.entity.Company;
import pl.tuatara.demo.model.entity.User;
import pl.tuatara.demo.model.exception.CompanyAlreadyExistsException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;
    private UserRepository userRepository;
    private LocationFetchingService locationFetchingService;
    private CompanyConverter companyConverter;

    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository,
                          LocationFetchingService locationFetchingService, CompanyConverter companyConverter) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.locationFetchingService = locationFetchingService;
        this.companyConverter = companyConverter;
    }

    public CompanyDto get(String name) {
        return companyConverter.convertToDto(companyRepository.findById(name).get());
    }

    public void create(CompanyDto companyDto) throws CompanyAlreadyExistsException, InterruptedException, ApiException, IOException {
        if (companyRepository.existsById(companyDto.getName()))
            throw new CompanyAlreadyExistsException();
        LatLng location = locationFetchingService.getCompanyLocation(companyDto);
        Company company = companyConverter.convertToCompany(companyDto);
        company.setLatitude(location.lat);
        company.setLongitude(location.lng);
        companyRepository.save(company);
    }

    //    TODO exceptions
    public void assignUser(String name, String username) {
        Company company = companyRepository.findById(name).get();
        User user = userRepository.findById(username).get();
        company.getUsers().add(user);
        companyRepository.save(company);
    }

    public List<CompanyDto> getAll() {
        return companyRepository.findAll()
                .stream().map(company -> {
                    CompanyDto companyDto = companyConverter.convertToDto(company);
                    companyDto.setUsers(null);
                    return companyDto;
                })
                .collect(Collectors.toList());
    }

    public void delete(String name) {
        companyRepository.deleteById(name);
    }

}