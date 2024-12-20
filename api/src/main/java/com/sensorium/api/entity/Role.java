package com.sensorium.api.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document("roles")
@Data
@Builder
public class Role {
	private RoleEnum name;
}
