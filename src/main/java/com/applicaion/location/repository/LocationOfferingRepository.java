package com.applicaion.location.repository;

import com.applicaion.location.entity.LocationOfferingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationOfferingRepository extends CrudRepository<LocationOfferingEntity, Long> {
    List<LocationOfferingEntity> findByLocationId(Long locationId);
}
