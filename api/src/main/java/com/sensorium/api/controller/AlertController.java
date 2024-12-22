package com.sensorium.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sensorium.api.service.impl.AlertService;

@RestController
@RequestMapping("/api")
public class AlertController {

	@Autowired
	private AlertService service;

	@Secured("ROLE_USER")
	@GetMapping("/user/alerts")
	public ResponseEntity<?> index(@RequestParam(name = "page", defaultValue = "1") Integer page) {
		return ResponseEntity.ok(service.getAll(page));
	}

	@Secured("ROLE_USER")
	@GetMapping("/user/alerts/{id}")
	public ResponseEntity<?> show(@PathVariable String id) {
		return ResponseEntity.ok(service.getDetails(id));
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/admin/alerts/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		return ResponseEntity.ok(service.delete(id));
	}
}
