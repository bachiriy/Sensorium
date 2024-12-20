package com.sensorium.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sensorium.api.dto.request.ZoneDtoReq;
import com.sensorium.api.dto.response.ZoneDtoResp;
import com.sensorium.api.entity.Zone;

@Mapper(componentModel = "spring")
public interface ZoneMapper {
	ZoneDtoResp entityToDto(Zone entity);

	List<ZoneDtoResp> entitiesToDtos(List<Zone> entities);

	Zone DtoToentity(ZoneDtoReq dto);
}
