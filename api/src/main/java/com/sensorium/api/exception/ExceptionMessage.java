package com.sensorium.api.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionMessage {
	private Integer status;
	private String error;
	private String message;
}
