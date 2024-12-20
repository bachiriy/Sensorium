package com.sensorium.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sensorium.api.dto.request.UserDtoReq;
import com.sensorium.api.dto.response.UserDtoResp;
import com.sensorium.api.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserDtoResp entityToDto(User entity);

	List<UserDtoResp> entitiesToDtos(List<User> entities);

	User DtoToentity(UserDtoReq dto);
}
