package com.sensorium.api.dto.response;

import java.util.Set;

import com.sensorium.api.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoResp {
	private String id;
	private String name;
	private String username;
	private Set<Role> roles;
	private Boolean enable;
}
