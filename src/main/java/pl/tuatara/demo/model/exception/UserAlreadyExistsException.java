package pl.tuatara.demo.model.exception;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String username) {
        super(String.format("User %s already exists", username));
    }

}