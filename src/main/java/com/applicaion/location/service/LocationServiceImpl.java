package com.applicaion.location.service;

import com.applicaion.location.apiclient.GeocodeApiService;
import com.applicaion.location.apiclient.OfferingApiService;
import com.applicaion.location.converter.LocationConverter;
import com.applicaion.location.entity.LocationEntity;
import com.applicaion.location.entity.LocationOfferingEntity;
import com.applicaion.location.model.LocationDetailModel;
import com.applicaion.location.model.LocationModel;
import com.applicaion.location.model.OfferingModel;
import com.applicaion.location.repository.LocationOfferingRepository;
import com.applicaion.location.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private GeocodeApiService geocodeApiService;

    @Autowired
    private LocationOfferingRepository locationOfferingRepository;

    @Autowired
    private OfferingApiService offerApiService;

    @Autowired
    private LocationConverter locationConverter;

    @Override
    public List<LocationModel> getAllLocation(Long userId) {
        List<LocationEntity> locationEntities = locationRepository.findByUserId(userId);
        List<LocationModel> locationModels = new ArrayList<>();

        for(LocationEntity locationEntity: locationEntities){
            //LocationModel locationModel = LocationMapper.INSTANCE.convertEntityToModel(locationEntity);
            LocationModel locationModel = locationConverter.convertEntityToModel(locationEntity);
            locationModels.add(locationModel);
        }
        logger.info("Returning location for a user");
        return locationModels;
    }

    @Override
    public boolean createLocation(LocationModel locationModel) {
            // Call the geocodeApiService to get coordinates
            geocodeApiService.getCoordinates(locationModel);

            // Convert LocationModel to LocationEntity
            //LocationEntity locationEntity = LocationMapper.INSTANCE.convertModelToEntity(locationModel);
            LocationEntity locationEntity = locationConverter.convertModelToEntity(locationModel);
            // Save LocationEntity to repository
            LocationEntity newLocation = locationRepository.save(locationEntity);

            // Save the mapping for Location to its offerings
            LocationOfferingEntity locationOfferingEntity = null;
            for(Long offeringId: locationModel.getOfferingIdList()){
                locationOfferingEntity = new LocationOfferingEntity();
                locationOfferingEntity.setOfferingId(offeringId);
                locationOfferingEntity.setLocationId(newLocation.getLocationId());
                locationOfferingRepository.save(locationOfferingEntity);
            }
            // Check if newLocation is not null
            if(newLocation != null) {
                return true;
            }
            return false;
    }

    @Override
    public LocationModel updateLocation(Long locationId, LocationModel locationModel, Long userId) {
        LocationEntity locationEntity = locationRepository.findByLocationIdAndUserId(locationId, userId);
        if (locationEntity != null && locationModel != null) {
            // Update only the fields that are provided in the locationModel
            if (locationModel.getCity() != null) {
                locationEntity.setCity(locationModel.getCity());
            }
            if (locationModel.getState() != null) {
                locationEntity.setState(locationModel.getState());
            }
            if (locationModel.getPlotNo() != null) {
                locationEntity.setPlotNo(locationModel.getPlotNo());
            }
            if (locationModel.getStreet() != null) {
                locationEntity.setStreet(locationModel.getStreet());
            }
            if (locationModel.getCountry() != null) {
                locationEntity.setCountry(locationModel.getCountry());
            }
            if (locationModel.getPincode() != null) {
                locationEntity.setPincode(locationModel.getPincode());
            }
            if(locationModel.getLocationType() != null) {
                locationEntity.setLocationType(locationModel.getLocationType());
            }

            LocationModel newLocationModel = locationConverter.convertEntityToModel(locationEntity);

            // Call the geocodeApiService to get coordinates if any address-related field is updated
            geocodeApiService.getCoordinates(newLocationModel);

            // Save the updated locationEntity
            LocationEntity updatedLocationEntity = locationRepository.save(locationEntity);

            // Convert LocationEntity to LocationDetailModel
            return locationConverter.convertEntityToModel(updatedLocationEntity);
        }
        return null;
    }

    @Override
    public boolean deleteLocation(Long locationId, Long userId) {
        LocationEntity locationEntity = locationRepository.findByLocationIdAndUserId(locationId, userId);
        if (locationEntity != null) {
            locationRepository.delete(locationEntity);
            return true;
        }
        return false;
    }

    @Override
    public LocationDetailModel getLocationDetail(Long locationId, Long userId) {

        LocationDetailModel locationDetailModel = null;
        LocationEntity locationEntity = locationRepository.findByLocationIdAndUserId(locationId, userId);

        if(locationEntity != null){
            locationDetailModel = new LocationDetailModel();
            locationDetailModel.setCity(locationEntity.getCity());
            locationDetailModel.setState(locationEntity.getState());
            locationDetailModel.setLat(locationEntity.getLat());
            locationDetailModel.setLon(locationEntity.getLon());
            locationDetailModel.setLocationId(locationEntity.getLocationId());
            locationDetailModel.setPlotNo(locationEntity.getPlotNo());
            locationDetailModel.setStreet(locationEntity.getStreet());
            locationDetailModel.setCountry(locationEntity.getCountry());
            locationDetailModel.setPincode(locationEntity.getPincode());
            locationDetailModel.setUserId(locationEntity.getUserId());
            locationDetailModel.setLocationType(locationEntity.getLocationType());

            List<OfferingModel> offeringModelList = new ArrayList<>();
            List<LocationOfferingEntity> offeringIdList = locationOfferingRepository.findByLocationId(locationId);
            for(LocationOfferingEntity locationOffering : offeringIdList) {
                //make call to offering api to fetch 1 offering detail
                OfferingModel offeringModel = offerApiService.fetchOfferingDetail(locationOffering.getOfferingId());
                offeringModelList.add(offeringModel);
            }

            locationDetailModel.setOfferingList(offeringModelList);
        }
        return locationDetailModel;
    }
}
