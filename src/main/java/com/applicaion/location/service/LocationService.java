package com.applicaion.location.service;

import com.applicaion.location.model.LocationDetailModel;
import com.applicaion.location.model.LocationModel;

import java.util.List;

public interface LocationService {

    List<LocationModel> getAllLocation(Long userId);

    boolean createLocation(LocationModel locationModel);

    LocationModel updateLocation(Long lId, LocationModel locationModel, Long userId);

    boolean deleteLocation(Long lId, Long userId);

    LocationDetailModel getLocationDetail(Long locationId,Long userId);

    }
