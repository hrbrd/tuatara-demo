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

import java.io.IOException;
import java.util.Date;

@ControllerAdvice(assignableTypes = CompanyController.class)
public class CompanyControllerExceptionHandler {

    private static final String COMPANY_ALREADY_EXISTS_MESSAGE = "Company with this name already exists";
    private static final String COMPANY_NOT_FOUND_MESSAGE = "Company with this name does not exists";
    private static final String GEOLOCATION_API_SERVICE_EXCEPTION_MESSAGE = "Service unavailable, please try again later";
    private static final String LOCATION_NOT_FOUND_MESSAGE = "Location of this company could not be found. Are the name and address correct?";

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity handleCompanyAlreadyExistsException() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ExceptionDetails(new Date(), COMPANY_ALREADY_EXISTS_MESSAGE));
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity handleCompanyNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDetails(new Date(), COMPANY_NOT_FOUND_MESSAGE));
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
    public ResponseEntity handleLocationNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDetails(new Date(), LOCATION_NOT_FOUND_MESSAGE));
    }

}