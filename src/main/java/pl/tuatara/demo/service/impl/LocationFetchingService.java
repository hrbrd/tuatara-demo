package pl.tuatara.demo.service.impl;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.tuatara.demo.model.Location;
import pl.tuatara.demo.model.dto.CompanyDto;
import pl.tuatara.demo.model.exception.LocationNotFoundException;
import pl.tuatara.demo.service.ILocationFetchingService;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class LocationFetchingService implements ILocationFetchingService {

    @Value("${geolocation-api.key}")
    private String apiKey;

    private GeoApiContext context;

    @PostConstruct
    public void init() {
        this.context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    @Override
    public Location fetchLocation(CompanyDto company) throws InterruptedException, ApiException, IOException, LocationNotFoundException {
        GeocodingResult[] results = GeocodingApi.geocode(context,
                String.format("%s %s %s %s", company.getName(), company.getStreet(), company.getCity(), company.getCountry()))
                .await();
        if(results.length == 0 || results == null)
            throw new LocationNotFoundException();
        LatLng location = results[0].geometry.location;
        return new Location(location.lat, location.lng);
    }

}
