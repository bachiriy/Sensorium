package com.sensorium.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sensorium.api.dto.response.AlertDtoResp;
import com.sensorium.api.entity.Alert;

@Mapper(componentModel = "spring")
public interface AlertMapper {
	AlertDtoResp entityToDto(Alert entity);

	List<AlertDtoResp> entitiesToDtos(List<Alert> entities);
}
