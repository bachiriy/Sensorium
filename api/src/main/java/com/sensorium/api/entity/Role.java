package com.sensorium.api.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sensorium.api.entity.enums.RoleEnum;

import lombok.Builder;
import lombok.Data;

@Document("roles")
@Data
@Builder
public class Role {
	@Id
	private String id;
	@NotNull(message = "Role name is required")
	@NotBlank(message = "Role name is required")
	private RoleEnum name;
}
