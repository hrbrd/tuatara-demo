package pl.tuatara.demo.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.tuatara.demo.model.ExceptionDetails;
import pl.tuatara.demo.model.exception.UserNotFoundException;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final String EMPTY_FIELDS_ERROR_MESSAGE = "Fields cannot be empty, please fill all fields";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDetails(new Date(), EMPTY_FIELDS_ERROR_MESSAGE));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDetails(new Date(), exception.getMessage()));
    }

}