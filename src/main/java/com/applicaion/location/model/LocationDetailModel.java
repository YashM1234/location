package com.applicaion.location.model;

import com.applicaion.location.constant.LocationType;
import lombok.Data;

import java.util.List;

@Data
public class LocationDetailModel {

    private Long locationId;
    private LocationType locationType;
    private String plotNo;
    private String street;
    private String pincode;
    private String city;
    private String state;
    private String country;
    private Long userId;
    private Long openCloseTimeId;
    private List<OfferingModel> offeringList;
    private String lat;
    private String lon;

}
