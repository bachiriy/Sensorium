package com.sensorium.api.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceDtoReq {
	@NotNull(message = "Device name is required")
	@NotBlank(message = "Device name is required")
	private String name;
	@NotNull(message = "Device type is required")
	private String typeName;
	private String statusName;
	@NotNull(message = "Zone id is required")
	@NotBlank(message = "Zone id is required")
	private String zoneId;
}
