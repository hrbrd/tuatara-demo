package pl.tuatara.demo.service;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;
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

    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository,
                          LocationFetchingService locationFetchingService) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.locationFetchingService = locationFetchingService;
    }

    public void create(CompanyDto companyDto) throws CompanyAlreadyExistsException, InterruptedException, ApiException, IOException {
        if (companyRepository.existsById(companyDto.getName()))
            throw new CompanyAlreadyExistsException();
        LatLng location = locationFetchingService.getCompanyLocation(companyDto);
        Company company = convertToCompany(companyDto);
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
                .stream().map(c -> convertToDto(c))
                .collect(Collectors.toList());
    }

    private Company convertToCompany(CompanyDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setStreet(companyDto.getStreet());
        company.setCity(companyDto.getCity());
        company.setCountry(companyDto.getCountry());
        company.setLatitude(companyDto.getLatitude());
        company.setLongitude(companyDto.getLongitude());

        return company;
    }

    private CompanyDto convertToDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName(company.getName());
        companyDto.setStreet(company.getStreet());
        companyDto.setCity(company.getCity());
        companyDto.setCountry(company.getCountry());
        companyDto.setLatitude(company.getLatitude());
        companyDto.setLongitude(company.getLongitude());

        return companyDto;
    }

}