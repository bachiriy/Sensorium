package com.sensorium.api.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sensorium.api.entity.enums.Severity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("alerts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alert {
	@NotNull(message = "Alert Serverity is required")
	private Severity severity;
	@NotNull(message = "Alert Message is required")
	@NotBlank(message = "Alert Message is required")
	private String message;
	@NotNull(message = "Alert Time is required")
	private LocalDateTime timestamp;

	@DBRef
	private Device device;
}
