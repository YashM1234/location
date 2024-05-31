package com.applicaion.location.mapper;

import com.applicaion.location.entity.LocationEntity;
import com.applicaion.location.model.LocationModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    LocationModel convertEntityToModel(LocationEntity locationEntity);
    LocationEntity convertModelToEntity(LocationModel locationModel);
}
