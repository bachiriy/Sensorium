package com.sensorium.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sensorium.api.dto.request.MeasureDtoReq;
import com.sensorium.api.dto.response.MeasureDtoResp;
import com.sensorium.api.entity.Measure;

@Mapper(componentModel = "spring")
public interface MeasureMapper {
	MeasureDtoResp entityToDto(Measure entity);

	List<MeasureDtoResp> entitiesToDtos(List<Measure> entities);

	Measure DtoToentity(MeasureDtoReq dto);
}
