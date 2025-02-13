package com.sensorium.api.entity;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document("users")
@Builder
@Data
public class User {
	@Id
	private String id;
	@NotNull(message = "User name is required")
	@NotBlank(message = "User name is required")
	private String name;

	@NotNull(message = "Username is required")
	@NotBlank(message = "Username is required")
	private String username;
	@NotNull(message = "User password is required")
	@NotBlank(message = "User password is required")
	private String password;
	@DBRef
	private Set<Role> roles;
	private Boolean enable;

	private Integer tentative;

}
