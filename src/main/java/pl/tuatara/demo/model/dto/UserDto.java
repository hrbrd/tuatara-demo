package pl.tuatara.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.tuatara.demo.model.entity.Company;
import pl.tuatara.demo.serializer.CompanyListSerializer;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class UserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @JsonSerialize(using = CompanyListSerializer.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Company> companies;

    public UserDto() { }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    @JsonIgnore
    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}
