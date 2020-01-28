package pl.tuatara.demo.model.exception;

public class CompanyAlreadyExistsException extends Exception {

    public CompanyAlreadyExistsException(String companyName) {
        super(String.format("Company %s already exists", companyName));
    }

}
