package com.applicaion.location.controller;

import com.applicaion.location.model.LocationDetailModel;
import com.applicaion.location.model.LocationModel;
import com.applicaion.location.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LocationController {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LocationService locationService;

    @GetMapping("/locations/users/{userId}")
    public ResponseEntity<List<LocationModel>> getAllLocation(@PathVariable("userId") Long userId) {
        logger.info("Getting all the locations for user with userId {}", userId);
        List<LocationModel> locationModels = locationService.getAllLocation(userId);
        return new ResponseEntity<>(locationModels, HttpStatus.OK);
    }

    @GetMapping("/locations/{locationId}/users/{userId}")
    public ResponseEntity<LocationDetailModel> getLocationDetail(@PathVariable("locationId") Long locationId, @PathVariable("userId") Long userId) {
        ResponseEntity<LocationDetailModel> responseEntity = null;
        LocationDetailModel locationDetailModel = locationService.getLocationDetail(locationId ,userId);
        if(locationDetailModel != null) {
            responseEntity = new ResponseEntity<>(locationDetailModel, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(locationDetailModel, HttpStatus.NOT_FOUND);

        }
        return responseEntity;
    }

    @PostMapping("/locations/users")
    public ResponseEntity<String> createLocation(@RequestBody LocationModel locationModel){
        boolean result = locationService.createLocation(locationModel);
        if(result){
            return new ResponseEntity<>("Location Created!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Location Not Found!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/locations/{locationId}/users/{userId}")
    public ResponseEntity<LocationModel> updateLocation(@PathVariable("locationId") Long locationId,
                               @RequestBody LocationModel locationModel, @PathVariable("userId") Long userId){
        LocationModel updatedLocation = locationService.updateLocation(locationId, locationModel, userId);
        if (updatedLocation != null) {
            return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("locations/{locationId}/users/{userId}")
    public ResponseEntity<String> deleteLocation(@PathVariable("locationId") Long locationId, @PathVariable("userId") Long userId){
        boolean result = locationService.deleteLocation(locationId, userId);
        if (result) {
            return new ResponseEntity<>("Location Deleted Successfully!", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Location Not Found!", HttpStatus.NOT_FOUND);
        }
    }















}
