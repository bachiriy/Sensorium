package com.sensorium.api.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("measures")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Measure {
	@NotNull(message = "Measure time is required")
	private LocalDateTime timestamp;
	@NotNull(message = "Measure value is required")
	private Double value;

	@DBRef
	private Device device;
}
