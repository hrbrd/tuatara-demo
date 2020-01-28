package pl.tuatara.demo.model.exception;

public class UserAlreadyAssignedException extends Exception {

    public UserAlreadyAssignedException(String username, String companyName) {
        super(String.format("User %s is already assigned to company %s", username, companyName));
    }

}
