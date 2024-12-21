package com.sensorium.api.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MeasureDtoReq {
	@NotNull(message = "Measure value is required")
	private Double value;
	@NotNull(message = "Device id is required")
	private String deviceId;
}
