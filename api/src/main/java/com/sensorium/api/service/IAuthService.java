package com.sensorium.api.service;

import javax.servlet.http.HttpServletResponse;

import com.sensorium.api.dto.request.LoginReq;
import com.sensorium.api.dto.request.UserDtoReq;
import com.sensorium.api.dto.response.LoginResp;
import com.sensorium.api.dto.response.UserDtoResp;

public interface IAuthService {
	LoginResp login(LoginReq dto, HttpServletResponse response);

	UserDtoResp register(UserDtoReq dto);
}
