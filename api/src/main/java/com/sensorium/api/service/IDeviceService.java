package com.sensorium.api.service;

import java.util.List;

import com.sensorium.api.dto.request.DeviceDtoReq;
import com.sensorium.api.dto.response.DeviceDtoResp;
import com.sensorium.api.dto.response.ResponseBody;
import com.sensorium.api.entity.Device;

public interface IDeviceService {
	Device getById(String id);

	List<DeviceDtoResp> getAll(Integer page);

	DeviceDtoResp getDetails(String id);

	DeviceDtoResp create(DeviceDtoReq dto);

	DeviceDtoResp update(DeviceDtoReq dto, String id);

	ResponseBody delete(String id);

	List<DeviceDtoResp> getDevicesByZone(String zoneId, Integer page);
}
