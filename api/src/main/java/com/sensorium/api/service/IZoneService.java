package com.sensorium.api.service;

import java.util.List;

import com.sensorium.api.dto.request.ZoneDtoReq;
import com.sensorium.api.dto.response.ResponseBody;
import com.sensorium.api.dto.response.ZoneDtoResp;
import com.sensorium.api.entity.Zone;

public interface IZoneService {
	Zone getById(String id);

	List<ZoneDtoResp> getAll(Integer page);

	ZoneDtoResp getDetails(String id);

	ZoneDtoResp create(ZoneDtoReq dto);

	ZoneDtoResp update(ZoneDtoReq dto, String id);

	ResponseBody delete(String id);
}
