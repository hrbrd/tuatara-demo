package pl.tuatara.demo.controller.handler;

import com.google.maps.errors.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.tuatara.demo.controller.CompanyController;
import pl.tuatara.demo.model.ExceptionDetails;
import pl.tuatara.demo.model.exception.CompanyAlreadyExistsException;
import pl.tuatara.demo.model.exception.CompanyNotFoundException;
import pl.tuatara.demo.model.exception.LocationNotFoundException;
import pl.tuatara.demo.model.exception.UserAlreadyAssignedException;

import java.io.IOException;
import java.util.Date;

@ControllerAdvice(assignableTypes = CompanyController.class)
public class CompanyControllerExceptionHandler {

    private static final String GEOLOCATION_API_SERVICE_EXCEPTION_MESSAGE = "Service unavailable, please try again later";

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity handleCompanyAlreadyExistsException(CompanyAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ExceptionDetails(new Date(), exception.getMessage()));
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity handleCompanyNotFoundException(CompanyNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDetails(new Date(), exception.getMessage()));
    }

    @ExceptionHandler({
            InterruptedException.class,
            ApiException.class,
            IOException.class
    })
    public ResponseEntity handleGeolocationApiServiceExceptions() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ExceptionDetails(new Date(), GEOLOCATION_API_SERVICE_EXCEPTION_MESSAGE));
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity handleLocationNotFoundException(LocationNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDetails(new Date(), exception.getMessage()));
    }

    @ExceptionHandler(UserAlreadyAssignedException.class)
    public ResponseEntity handleUserAlreadyAssignedException(UserAlreadyAssignedException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ExceptionDetails(new Date(), exception.getMessage()));
    }

}