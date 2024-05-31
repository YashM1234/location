package com.applicaion.location.converter;

import com.applicaion.location.entity.LocationEntity;
import com.applicaion.location.model.LocationModel;
import org.springframework.stereotype.Component;

@Component
public class LocationConverter {
    public LocationEntity convertModelToEntity(LocationModel locationModel){

        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCity(locationModel.getCity());
        locationEntity.setCountry(locationModel.getCountry());
        locationEntity.setLat(locationModel.getLat());
        locationEntity.setLon(locationModel.getLon());
        locationEntity.setLocationType(locationModel.getLocationType());
        locationEntity.setPincode(locationModel.getPincode());
        locationEntity.setPlotNo(locationModel.getPlotNo());
        locationEntity.setState(locationModel.getState());
        locationEntity.setStreet(locationModel.getStreet());
        locationEntity.setUserId(locationModel.getUserId());

        return locationEntity;
    }

    public LocationModel convertEntityToModel(LocationEntity locationEntity){

        LocationModel locationModel = new LocationModel();
        locationModel.setCity(locationEntity.getCity());
        locationModel.setCountry(locationEntity.getCountry());
        locationModel.setLat(locationEntity.getLat());
        locationModel.setLon(locationEntity.getLon());
        locationModel.setLocationType(locationEntity.getLocationType());
        locationModel.setPincode(locationEntity.getPincode());
        locationModel.setPlotNo(locationEntity.getPlotNo());
        locationModel.setState(locationEntity.getState());
        locationModel.setStreet(locationEntity.getStreet());
        locationModel.setUserId(locationModel.getUserId());
        locationModel.setLocationId(locationEntity.getLocationId());

        return locationModel;
    }
}
