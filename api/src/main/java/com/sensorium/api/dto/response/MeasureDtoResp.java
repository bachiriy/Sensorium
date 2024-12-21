package com.sensorium.api.dto.response;

import java.time.LocalDateTime;

import com.sensorium.api.entity.Device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MeasureDtoResp {
	private String id;
	private LocalDateTime timestamp;
	private Double value;
	private Device device;
}
