package pl.tuatara.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
public class User {

    @Id
    private String username;

    private String firstName;
    private String lastName;

    public User() {

    }

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
}