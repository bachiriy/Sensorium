package com.sensorium.api.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.sensorium.api.entity.enums.DeviceStatus;
import com.sensorium.api.entity.enums.DeviceType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("devices")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Device {
	@Id
	private String id;
	@NotNull(message = "Device name is required")
	@NotBlank(message = "Device name is required")
	private String name;
	@NotNull(message = "Device type is required")
	private DeviceType type;
	@NotNull(message = "Device status is required")
	private DeviceStatus status;
	@NotNull(message = "Device Last Communication is required")
	@Field(name = "last_communication")
	private LocalDateTime lastCommunication;

	private Zone zone;

	private List<Alert> alerts;

	private List<Measure> measures;
}
