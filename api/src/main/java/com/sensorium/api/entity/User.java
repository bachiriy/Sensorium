package com.sensorium.api.entity;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document("users")
@Builder
@Data
public class User {
	@Id
	private String id;
	private String name;
	@Indexed(unique = true)
	private String username;
	private String password;
	private Set<Role> roles;
	private Boolean enable;

}
