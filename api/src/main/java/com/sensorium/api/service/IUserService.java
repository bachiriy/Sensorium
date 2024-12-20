package com.sensorium.api.service;

import java.util.List;

import com.sensorium.api.dto.request.RoleDto;
import com.sensorium.api.dto.response.ResponseBody;
import com.sensorium.api.dto.response.UserDtoResp;
import com.sensorium.api.entity.User;

public interface IUserService {
	User getById(String id);

	List<UserDtoResp> getAll(Integer page);

	ResponseBody assignRole(String id, RoleDto dto);

	ResponseBody unassignRole(String id, RoleDto dto);

}
