package com.sensorium.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensorium.api.dto.request.DeviceDtoReq;
import com.sensorium.api.dto.response.DeviceDtoResp;
import com.sensorium.api.dto.response.ResponseBody;
import com.sensorium.api.entity.Device;
import com.sensorium.api.entity.Zone;
import com.sensorium.api.entity.enums.DeviceStatus;
import com.sensorium.api.entity.enums.DeviceType;
import com.sensorium.api.exception.ResourceNotFoundException;
import com.sensorium.api.mapper.DeviceMapper;
import com.sensorium.api.repository.DeviceRepository;
import com.sensorium.api.service.IDeviceService;

@Service
public class DeviceService implements IDeviceService {

	@Autowired
	private DeviceRepository repository;

	@Autowired
	private ZoneService zoneService;

	@Autowired
	private DeviceMapper mapper;

	@Override
	public Device getById(String id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Device Not Found"));
	}

	@Override
	public List<DeviceDtoResp> getAll(Integer page) {
		int size = 3;

		Pageable pageable = PageRequest.of(page - 1, size);
		List<Device> devices = repository.findAll(pageable).getContent();
		return mapper.entitiesToDtos(devices);
	}

	@Override
	public DeviceDtoResp getDetails(String id) {
		return mapper.entityToDto(getById(id));
	}

	@Override
	public DeviceDtoResp create(DeviceDtoReq dto) {
		Zone zone = zoneService.getById(dto.getZoneId());

		Device device = mapper.DtoToentity(dto);

		try {
			device.setType(DeviceType.valueOf(dto.getTypeName()));
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Type name is wrong");
		}
		device.setZone(zone);
		device.setStatus(DeviceStatus.ACTIVE);

		return mapper.entityToDto(repository.save(device));
	}

	@Override
	public DeviceDtoResp update(DeviceDtoReq dto, String id) {
		Device device = getById(id);
		Zone zone = zoneService.getById(dto.getZoneId());

		try {
			if (dto.getStatusName() != null) {
				device.setStatus(DeviceStatus.valueOf(dto.getStatusName()));
			}
			device.setType(DeviceType.valueOf(dto.getTypeName()));
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e.getMessage());
		}
		device.setName(dto.getName());
		device.setZone(zone);

		return mapper.entityToDto(repository.save(device));
	}

	@Override
	public ResponseBody delete(String id) {
		Device device = getById(id);
		repository.delete(device);
		return ResponseBody.builder().message("Device is deleted successfully").build();
	}

	@Override
	public List<DeviceDtoResp> getDevicesByZone(String zoneId, Integer page) {
		int size = 3;
		Pageable pageable = PageRequest.of(page - 1, size);
		List<Device> devices = repository.findByZoneId(zoneId, pageable);
		return mapper.entitiesToDtos(devices);
	}

}
