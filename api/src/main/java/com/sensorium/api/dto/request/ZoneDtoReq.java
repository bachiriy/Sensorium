package com.sensorium.api.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ZoneDtoReq {
	@NotNull(message = "Zone name is required")
	@NotBlank(message = "Zone name is required")
	private String name;
	@NotNull(message = "Zone type is required")
	@NotBlank(message = "Zone type is required")
	private String type;
	@NotNull(message = "Zone location is required")
	@NotBlank(message = "Zone location is required")
	private String location;
}
