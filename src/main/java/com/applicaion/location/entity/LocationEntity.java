package com.applicaion.location.entity;

import com.applicaion.location.constant.LocationType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "LOCATION_TABLE")
@Data
@NoArgsConstructor
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LOCATION_ID")
    private long locationId;
    @Enumerated(EnumType.STRING)
    private LocationType locationType;
    private String plotNo;
    private String street;
    private String pincode;
    private String city;
    private String state;
    private String country;
    private Long userId;
    private Long openCloseTimeId;
    private String lat;
    private String lon;
}
