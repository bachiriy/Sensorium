package com.sensorium.api.service;

import java.util.List;

import com.sensorium.api.dto.request.MeasureDtoReq;
import com.sensorium.api.dto.response.MeasureDtoResp;
import com.sensorium.api.dto.response.ResponseBody;
import com.sensorium.api.entity.Measure;

public interface IMeasureService {
	Measure getbyId(String id);

	List<MeasureDtoResp> getAll(Integer page);

	MeasureDtoResp getDetails(String id);

	MeasureDtoResp create(MeasureDtoReq dto);

	MeasureDtoResp update(MeasureDtoReq dto, String id);

	ResponseBody delete(String id);

	List<MeasureDtoResp> getDeviceMeasures(String deviceId, Integer page);

	byte[] generateMeasuresCsv();

}
