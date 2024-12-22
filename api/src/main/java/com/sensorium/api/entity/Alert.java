package com.sensorium.api.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sensorium.api.entity.enums.DeviceType;
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
	@Id
	private String id;
	@NotNull(message = "Alert Serverity is required")
	private Severity severity;
	@NotNull(message = "Alert Message is required")
	@NotBlank(message = "Alert Message is required")
	private String message;
	@NotNull(message = "Alert Time is required")
	private LocalDateTime timestamp;

	@DBRef
	private Device device;

	public Alert(DeviceType deviceType, Double value) {
		if (deviceType.equals(DeviceType.HUMIDITY_SENSOR)) {
			checkHumidity(value);
		} else {
			checkTemperature(value);
		}
	}

	private void checkTemperature(Double value) {
		if (value > 40 || value < -10) {
			message = "Immediate risk for equipment";
			severity = Severity.CRITICAL;
		} else if ((value > 35 && value <= 40) || (value > -5 && value <= -10)) {
			message = "Worrying situation requiring rapid action";
			severity = Severity.HIGH;
		} else if ((value > 30 && value <= 35) || (value >= 0 && value <= -5)) {
			message = "Situation to monitor";
			severity = Severity.MEDIUM;
		} else if ((value > 25 && value <= 30)) {
			message = "Slight deviation from optimal values";
			severity = Severity.LOW;
		} else if (value >= 20 && value <= 25) {
			message = "Temperature in the optimal range";
			severity = Severity.NORMAL;
		}

	}

	private void checkHumidity(Double value) {
		if (value > 0.9 || value < 0.2) {
			message = "Risk of material damage";
			severity = Severity.CRITICAL;
		} else if ((value > 0.8 && value <= 0.9) || (value > 0.2 && value <= 0.3)) {
			message = "Unfavorable conditions";
			severity = Severity.HIGH;
		} else if ((value > 0.7 && value <= 0.8) || (value > 0.3 && value <= 0.4)) {
			message = "Situation to monitor";
			severity = Severity.MEDIUM;
		} else if ((value > 0.65 && value <= 0.7) || (value > 0.4 && value <= 0.45)) {
			message = "Slight deviation";
			severity = Severity.LOW;
		} else if (value > 0.45 && value <= 0.65) {
			message = "Humidity in the optimal range";
			severity = Severity.NORMAL;
		}
	}

}
