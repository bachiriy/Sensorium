package com.sensorium.api.entity;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("zones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Zone {
	@NotNull(message = "Zone name is required")
	@NotBlank(message = "Zone name is required")
	private String name;
	@NotNull(message = "Zone type is required")
	@NotBlank(message = "Zone type is required")
	private String type;
	@NotNull(message = "Zone location is required")
	@NotBlank(message = "Zone location is required")
	private String location;

	private List<Device> devices;
}
