package com.sensorium.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensorium.api.dto.response.AlertDtoResp;
import com.sensorium.api.dto.response.ResponseBody;
import com.sensorium.api.entity.Alert;
import com.sensorium.api.exception.ResourceNotFoundException;
import com.sensorium.api.mapper.AlertMapper;
import com.sensorium.api.repository.AlertRepository;
import com.sensorium.api.service.IAlertService;

@Service
public class AlertService implements IAlertService {

	@Autowired
	private AlertRepository repository;

	@Autowired
	private AlertMapper mapper;

	@Override
	public Alert getById(String id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Alert Not Found"));
	}

	@Override
	public List<AlertDtoResp> getAll(Integer page) {
		int size = 3;
		Pageable pageable = PageRequest.of(page - 1, size);
		List<Alert> alerts = repository.findAll(pageable).getContent();
		return mapper.entitiesToDtos(alerts);
	}

	@Override
	public AlertDtoResp getDetails(String id) {
		Alert alert = getById(id);
		return mapper.entityToDto(alert);
	}

	@Override
	public ResponseBody delete(String id) {
		Alert alert = getById(id);
		repository.delete(alert);
		return ResponseBody.builder().message("Alert is deleted successfully").build();
	}

}
