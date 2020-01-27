package pl.tuatara.demo.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.tuatara.demo.model.dto.CompanyDto;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class LocationFetchingService {

    @Value("${geolocation-api.key}")
    private String apiKey;

    private GeoApiContext context;

    @PostConstruct
    public void init() {
        this.context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    public LatLng getCompanyLocation(CompanyDto company) throws InterruptedException, ApiException, IOException {
        GeocodingResult[] results = GeocodingApi.geocode(context,
                String.format("%s %s %s %s", company.getName(), company.getStreet(), company.getCity(), company.getCountry()))
                .await();
//        if(results == null)
//            throw new Exception();
        return results[0].geometry.location;
    }

}