package pl.tuatara.demo.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "companies")
public class Company {
    @Id
    private String name;

    private String street;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;

    @ManyToMany
    @JoinTable(name = "companies_users",
            joinColumns = @JoinColumn(name = "companies_name"),
            inverseJoinColumns = @JoinColumn(name = "users_username"))
    private List<User> users;

    public Company() {

    }

    public Company(String name, String street, String city, String country) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public Company(String name, String street, String city, String country, Double latitude, Double longitude) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}