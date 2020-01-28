package pl.tuatara.demo.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.tuatara.demo.controller.UserController;
import pl.tuatara.demo.model.ExceptionDetails;
import pl.tuatara.demo.model.exception.UserAlreadyExistsException;

import java.util.Date;

@ControllerAdvice(assignableTypes = UserController.class)
public class UserControllerExceptionHandler {

    private static final String USER_ALREADY_EXISTS_MESSAGE = "User with this username already exists";

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity handleUserAlreadyExistsException() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ExceptionDetails(new Date(), USER_ALREADY_EXISTS_MESSAGE));
    }

}