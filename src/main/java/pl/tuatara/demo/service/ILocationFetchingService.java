package pl.tuatara.demo.service;

import pl.tuatara.demo.model.Location;
import pl.tuatara.demo.model.dto.CompanyDto;

public interface ILocationFetchingService {
    Location fetchLocation(CompanyDto companyDto) throws Exception;
}
