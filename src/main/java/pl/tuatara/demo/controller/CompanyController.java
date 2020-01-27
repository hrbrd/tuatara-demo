package pl.tuatara.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tuatara.demo.model.dto.CompanyDto;
import pl.tuatara.demo.model.exception.CompanyAlreadyExistsException;
import pl.tuatara.demo.service.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public void create(@RequestBody CompanyDto company) throws CompanyAlreadyExistsException {
        companyService.create(company);
    }

    @PutMapping("/{name}/assign/{username}")
    public void assignUser(@PathVariable String name, @PathVariable String username) {
        companyService.assignUser(name, username);
    }

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity handleCompanyAlreadyExistsException() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("This company already exists");
    }

}