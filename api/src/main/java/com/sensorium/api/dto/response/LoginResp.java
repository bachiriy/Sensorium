package com.sensorium.api.dto.response;

import java.util.Set;

import com.sensorium.api.entity.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResp {
	private String name;
	private String username;
	private Set<Role> roles;
	private Boolean enable;
}
