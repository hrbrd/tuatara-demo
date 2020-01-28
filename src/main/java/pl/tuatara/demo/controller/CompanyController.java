package pl.tuatara.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.tuatara.demo.model.dto.CompanyDto;
import pl.tuatara.demo.model.exception.CompanyNotFoundException;
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

    @GetMapping("/{name}")
    public CompanyDto get(@PathVariable String name) throws CompanyNotFoundException {
        return companyService.get(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody @Valid CompanyDto company) throws Exception {
        companyService.create(company);
    }

    @PutMapping("/{name}/assign/{username}")
    public void assignUser(@PathVariable String name, @PathVariable String username) throws CompanyNotFoundException, UserNotFoundException {
        companyService.assignUser(name, username);
    }

    @GetMapping
    public List<CompanyDto> getAll() {
        return companyService.getAll();
    }

    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) throws CompanyNotFoundException {
        companyService.delete(name);
    }

}