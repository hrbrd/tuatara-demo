package pl.tuatara.demo.controller;

import com.google.maps.errors.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tuatara.demo.model.dto.CompanyDto;
import pl.tuatara.demo.model.exception.CompanyAlreadyExistsException;
import pl.tuatara.demo.service.ICompanyService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private ICompanyService companyService;

    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{name}")
    public CompanyDto getCompany(@PathVariable String name) {
        return companyService.get(name);
    }

    @PostMapping
    public void create(@RequestBody CompanyDto company) throws Exception {
        companyService.create(company);
    }

    @PutMapping("/{name}/assign/{username}")
    public void assignUser(@PathVariable String name, @PathVariable String username) {
        companyService.assignUser(name, username);
    }

    @GetMapping
    public List<CompanyDto> getAll() {
        return companyService.getAll();
    }

    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        companyService.delete(name);
    }

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity handleCompanyAlreadyExistsException() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("This company already exists");
    }

    @ExceptionHandler({
            InterruptedException.class,
            ApiException.class,
            IOException.class
    })
    public ResponseEntity handleGeolocationApiServiceExceptions() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Service unavailable, please try again later");
    }

}