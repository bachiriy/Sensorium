package com.sensorium.api.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensorium.api.dto.request.MeasureDtoReq;
import com.sensorium.api.dto.response.MeasureDtoResp;
import com.sensorium.api.dto.response.ResponseBody;
import com.sensorium.api.entity.Alert;
import com.sensorium.api.entity.Device;
import com.sensorium.api.entity.Measure;
import com.sensorium.api.exception.ResourceNotFoundException;
import com.sensorium.api.mapper.MeasureMapper;
import com.sensorium.api.repository.AlertRepository;
import com.sensorium.api.repository.DeviceRepository;
import com.sensorium.api.repository.MeasureRepository;
import com.sensorium.api.service.IMeasureService;
import com.sensorium.api.utils.CsvGenerator;

@Service
public class MeasureService implements IMeasureService {

	@Autowired
	private MeasureRepository repository;

	@Autowired
	private MeasureMapper mapper;

	@Autowired
	private CsvGenerator csvGenerator;

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private AlertRepository alertRepository;

	@Override
	public Measure getbyId(String id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Measure Not Found"));
	}

	@Override
	public List<MeasureDtoResp> getAll(Integer page) {
		int size = 3;
		Pageable pageable = PageRequest.of(page - 1, size);

		List<Measure> measures = repository.findAll(pageable).getContent();

		return mapper.entitiesToDtos(measures);
	}

	@Override
	public MeasureDtoResp getDetails(String id) {
		return mapper.entityToDto(getbyId(id));
	}

	@Override
	public MeasureDtoResp create(MeasureDtoReq dto) {
		Device device = deviceService.getById(dto.getDeviceId());
		Measure measure = mapper.DtoToentity(dto);
		LocalDateTime now = LocalDateTime.now();
		measure.setDevice(device);
		measure.setTimestamp(now);

		repository.save(measure);

		Alert alert = new Alert(device.getType(), dto.getValue());
		alert.setTimestamp(now);
		alert.setDevice(device);
		alertRepository.save(alert);

		device.setLastCommunication(now);
		deviceRepository.save(device);

		return mapper.entityToDto(measure);
	}

	@Override
	public MeasureDtoResp update(MeasureDtoReq dto, String id) {
		Measure measure = getbyId(id);
		Device device = deviceService.getById(dto.getDeviceId());
		measure.setValue(dto.getValue());
		measure.setDevice(device);

		repository.save(measure);

		Alert alert = new Alert(device.getType(), dto.getValue());
		alert.setTimestamp(LocalDateTime.now());
		alert.setDevice(device);
		alertRepository.save(alert);

		device.setLastCommunication(LocalDateTime.now());
		deviceRepository.save(device);

		return mapper.entityToDto(measure);
	}

	@Override
	public ResponseBody delete(String id) {
		Measure measure = getbyId(id);

		repository.delete(measure);

		return ResponseBody.builder().message("Measure Deleted Successfully").build();
	}

	@Override
	public List<MeasureDtoResp> getDeviceMeasures(String deviceId, Integer page) {
		int size = 3;
		Pageable pageable = PageRequest.of(page - 1, size);
		List<Measure> measures = repository.findByDeviceId(deviceId, pageable);
		return mapper.entitiesToDtos(measures);
	}

	@Override
	public byte[] generateMeasuresCsv() {
		List<Measure> measures = repository.findAll();
		return csvGenerator.generateCsv(measures);
	}

}
