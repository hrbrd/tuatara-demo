package pl.tuatara.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.tuatara.demo.model.entity.User;
import pl.tuatara.demo.serializer.UserListSerializer;

import java.util.List;

public class CompanyDto {
    private String name;
    private String street;
    private String city;
    private String country;
    private double latitude;
    private double longitude;

    @JsonSerialize(using = UserListSerializer.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<User> users;

    public CompanyDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
