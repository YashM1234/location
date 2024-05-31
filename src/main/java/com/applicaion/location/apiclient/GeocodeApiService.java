package com.applicaion.location.apiclient;

import com.applicaion.location.model.LocationModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocodeApiService {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Value("${geocode.api.baseurl}")
    private String apiBaseUrl;

    @Value("${geocode.api.key}")
    private String apiKey;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @Cacheable(cacheNames = "geoCodeCache", key = "#locationModel.userId")
    public void getCoordinates(LocationModel locationModel){
        StringBuilder apiUrl = new StringBuilder();
        apiUrl.append(apiBaseUrl);
        apiUrl.append("?access_key=");
        apiUrl.append(apiKey);
        apiUrl.append("&query=");
        if(null != locationModel.getStreet()) {
            apiUrl.append(locationModel.getStreet());
            apiUrl.append(" ");
        }
        if(null != locationModel.getCity()) {
            apiUrl.append(locationModel.getCity());
            apiUrl.append(" ");
        }
        if(null != locationModel.getCountry()) {
            apiUrl.append(locationModel.getCountry());
        }

        try {
            GeocodeResponseWrapper responseWrapper = restTemplate.getForObject(apiUrl.toString(), GeocodeResponseWrapper.class);

            assert responseWrapper != null;
            if (responseWrapper.getGeocodeList() != null && !responseWrapper.getGeocodeList().isEmpty()) {
                GeocodeResponse response = responseWrapper.getGeocodeList().get(0);
                locationModel.setLat(response.getLatitude().toString());
                locationModel.setLon(response.getLongitude().toString());
            }
        }catch(Exception ex){
            logger.error("Error occurred while connecting to geocode api at {}", apiUrl);
        }
    }

}
