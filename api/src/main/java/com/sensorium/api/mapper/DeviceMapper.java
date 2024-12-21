package com.sensorium.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sensorium.api.dto.request.DeviceDtoReq;
import com.sensorium.api.dto.response.DeviceDtoResp;
import com.sensorium.api.entity.Device;

@Mapper(componentModel = "spring")
public interface DeviceMapper {
	DeviceDtoResp entityToDto(Device entity);

	List<DeviceDtoResp> entitiesToDtos(List<Device> entities);

	Device DtoToentity(DeviceDtoReq dto);
}
