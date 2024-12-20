package com.sensorium.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document("roles")
@Data
@Builder
public class Role {
	@Id
	private String id;
	private RoleEnum name;
}
