package com.sensorium.api.service;

import java.util.List;

import com.sensorium.api.dto.response.AlertDtoResp;
import com.sensorium.api.dto.response.ResponseBody;
import com.sensorium.api.entity.Alert;

public interface IAlertService {

	Alert getById(String id);

	List<AlertDtoResp> getAll(Integer page);

	AlertDtoResp getDetails(String id);

	ResponseBody delete(String id);

}
