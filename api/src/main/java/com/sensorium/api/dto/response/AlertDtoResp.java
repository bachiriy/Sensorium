package com.sensorium.api.dto.response;

import java.time.LocalDateTime;

import com.sensorium.api.entity.Device;
import com.sensorium.api.entity.enums.Severity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlertDtoResp {
	private String id;
	private Severity severity;
	private String message;
	private LocalDateTime timestamp;
	private Device device;
}
