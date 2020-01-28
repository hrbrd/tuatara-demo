package pl.tuatara.demo.model.exception;

public class CompanyNotFoundException extends Exception {

    public CompanyNotFoundException(String companyName) {
        super(String.format("Company %s not found", companyName));
    }

}
