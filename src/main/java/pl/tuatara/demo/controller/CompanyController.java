package pl.tuatara.demo.controller;

import org.springframework.web.bind.annotation.*;
import pl.tuatara.demo.model.dto.CompanyDto;
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
    public CompanyDto getCompany(@PathVariable String name) {
        return companyService.get(name);
    }

    @PostMapping
    public void create(@RequestBody @Valid CompanyDto company) throws Exception {
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

}