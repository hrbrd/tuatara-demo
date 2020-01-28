package pl.tuatara.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.tuatara.demo.model.dto.CompanyDto;
import pl.tuatara.demo.model.exception.CompanyNotFoundException;
import pl.tuatara.demo.model.exception.UserAlreadyAssignedException;
import pl.tuatara.demo.model.exception.UserNotFoundException;
import pl.tuatara.demo.service.ICompanyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private ICompanyService companyService;

    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{companyName}")
    public CompanyDto get(@PathVariable String companyName) throws CompanyNotFoundException {
        return companyService.get(companyName);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody @Valid CompanyDto company) throws Exception {
        companyService.create(company);
    }

    @PutMapping("/{companyName}/assign/{username}")
    public void assignUser(@PathVariable String companyName, @PathVariable String username) throws CompanyNotFoundException, UserNotFoundException, UserAlreadyAssignedException {
        companyService.assignUser(companyName, username);
    }

    @GetMapping
    public List<CompanyDto> getAll() {
        return companyService.getAll();
    }

    @DeleteMapping("/{companyName}")
    public void delete(@PathVariable String companyName) throws CompanyNotFoundException {
        companyService.delete(companyName);
    }

}