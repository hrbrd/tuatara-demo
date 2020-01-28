package pl.tuatara.demo.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.tuatara.demo.controller.UserController;
import pl.tuatara.demo.model.exception.UserAlreadyExistsException;

@ControllerAdvice(assignableTypes = UserController.class)
public class UserControllerExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity handleUserAlreadyExistsException() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("This user already exists");
    }

}