package pl.tuatara.demo.model.exception;

public class LocationNotFoundException extends Exception {

    public LocationNotFoundException(String companyName) {
        super(String.format("%s location could not be found. Are the name and address correct?", companyName));
    }

}