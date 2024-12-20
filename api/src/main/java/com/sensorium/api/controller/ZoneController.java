package com.sensorium.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sensorium.api.dto.request.ZoneDtoReq;
import com.sensorium.api.service.impl.ZoneService;

@RestController
@RequestMapping("/api")
public class ZoneController {
	@Autowired
	private ZoneService service;

	@GetMapping("/user/zones")
	public ResponseEntity<?> index(@RequestParam(defaultValue = "0", name = "page") Integer page) {
		return ResponseEntity.ok(service.getAll(page));
	}

	@GetMapping("/user/zones/{id}")
	public ResponseEntity<?> show(@PathVariable String id) {
		return ResponseEntity.ok(service.getDetails(id));
	}

	@PostMapping("/admin/zones")
	public ResponseEntity<?> store(@RequestBody @Valid ZoneDtoReq dto) {
		return ResponseEntity.ok(service.create(dto));
	}

}
