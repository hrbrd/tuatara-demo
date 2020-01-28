package pl.tuatara.demo.controller.handler;

import com.google.maps.errors.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.tuatara.demo.controller.CompanyController;
import pl.tuatara.demo.model.exception.CompanyAlreadyExistsException;

import java.io.IOException;

@ControllerAdvice(assignableTypes = CompanyController.class)
public class CompanyControllerExceptionHandler {

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity handleCompanyAlreadyExistsException() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("This company already exists");
    }

    @ExceptionHandler({
            InterruptedException.class,
            ApiException.class,
            IOException.class
    })
    public ResponseEntity handleGeolocationApiServiceExceptions() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Service unavailable, please try again later");
    }

}