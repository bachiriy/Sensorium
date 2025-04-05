package com.sensorium.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sensorium.api.dto.request.DeviceDtoReq;
import com.sensorium.api.service.impl.DeviceService;

@RestController
@RequestMapping("/api")
public class DeviceController {
	@Autowired
	private DeviceService service;

	// @Secured("ROLE_USER")
	@GetMapping("/user/devices")
	public ResponseEntity<?> index(@RequestParam(defaultValue = "1", name = "page") Integer page) {
		return ResponseEntity.ok(service.getAll(page));
	}

	@Secured("ROLE_USER")
	@GetMapping("/user/devices/{id}")
	public ResponseEntity<?> show(@PathVariable String id) {
		return ResponseEntity.ok(service.getDetails(id));
	}

	// @Secured("ROLE_ADMIN")
	@PostMapping("/admin/devices")
	public ResponseEntity<?> store(@RequestBody @Valid DeviceDtoReq dto) {
		return ResponseEntity.ok(service.create(dto));
	}

	@Secured("ROLE_ADMIN")p
	@PutMapping("/admin/devices/{id}")
	public ResponseEntity<?> update(@RequestBody @Valid DeviceDtoReq dto, @PathVariable String id) {
		return ResponseEntity.ok(service.update(dto, id));
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/admin/devices/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {

		return ResponseEntity.ok(service.delete(id));
	}

	@Secured("ROLE_USER")
	@GetMapping("/user/devices/zone/{zoneId}")
	public ResponseEntity<?> searchByZone(@RequestParam(defaultValue = "1", name = "page") Integer page,
			@PathVariable String zoneId) {
		return ResponseEntity.ok(service.getDevicesByZone(zoneId, page));
	}
}
