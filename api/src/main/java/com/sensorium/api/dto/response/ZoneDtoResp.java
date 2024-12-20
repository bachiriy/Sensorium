package com.sensorium.api.dto.response;

import java.util.List;

import com.sensorium.api.entity.Device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZoneDtoResp {
	private String id;
	private String name;
	private String type;
	private String location;
	private List<Device> devices;
}
