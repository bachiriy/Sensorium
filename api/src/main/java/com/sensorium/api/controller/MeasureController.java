package com.sensorium.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sensorium.api.dto.request.MeasureDtoReq;
import com.sensorium.api.service.impl.MeasureService;

@RestController
@RequestMapping("/api")
public class MeasureController {
	@Autowired
	private MeasureService service;

	@GetMapping("/user/measures")
	public ResponseEntity<?> index(@RequestParam(defaultValue = "1", name = "page") Integer page) {
		return ResponseEntity.ok(service.getAll(page));
	}

	@GetMapping("/user/measures/{id}")
	public ResponseEntity<?> show(@PathVariable String id) {
		return ResponseEntity.ok(service.getDetails(id));
	}

	@PostMapping("/admin/measures")
	public ResponseEntity<?> store(@RequestBody @Valid MeasureDtoReq dto) {
		return ResponseEntity.ok(service.create(dto));
	}

	@PutMapping("/admin/measures/{id}")
	public ResponseEntity<?> update(@RequestBody @Valid MeasureDtoReq dto, @PathVariable String id) {
		return ResponseEntity.ok(service.update(dto, id));
	}

	@DeleteMapping("/admin/measures/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {

		return ResponseEntity.ok(service.delete(id));
	}

	@GetMapping("/user/measures/device/{deviceId}")
	public ResponseEntity<?> searchByDevice(@RequestParam(defaultValue = "1", name = "page") Integer page,
			@PathVariable String deviceId) {
		return ResponseEntity.ok(service.getDeviceMeasures(deviceId, page));
	}

	@GetMapping("/user/measures/export")
	public ResponseEntity<?> exportMeasures() {
		byte[] csv = service.generateMeasuresCsv();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "measures.csv");
		return new ResponseEntity<>(csv, headers, HttpStatus.OK);
	}
}
