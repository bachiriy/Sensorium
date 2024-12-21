package com.sensorium.api.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.sensorium.api.entity.Alert;
import com.sensorium.api.entity.Measure;
import com.sensorium.api.entity.Zone;
import com.sensorium.api.entity.enums.DeviceStatus;
import com.sensorium.api.entity.enums.DeviceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceDtoResp {
	private String id;
	private String name;
	private DeviceType type;
	private DeviceStatus status;
	private LocalDateTime lastCommunication;
	private Zone zone;
	private List<Alert> alerts;
	private List<Measure> measures;
}
