package com.applicaion.location.model;

import com.applicaion.location.constant.LocationType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationModel {

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
    private Set<Long> offeringIdList;
    private String lat;
    private String lon;
}
