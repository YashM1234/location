package com.applicaion.location.repository;

import com.applicaion.location.entity.LocationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<LocationEntity, Long> {
    List<LocationEntity> findByUserId(Long userId);
    LocationEntity findByLocationIdAndUserId(Long locationId, Long userId);
}
