package com.sensorium.api.dto.request;

import com.sensorium.api.entity.RoleEnum;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
	private RoleEnum role;
}
