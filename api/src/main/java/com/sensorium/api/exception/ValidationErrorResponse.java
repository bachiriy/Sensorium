package com.sensorium.api.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class ValidationErrorResponse {
	private LocalDateTime timestamp;
	private int status;
	private String message;
	private Map<String, String> errors = new HashMap<>();

	public void addError(String field, String message) {
		errors.put(field, message);
	}
}
