package com.sensorium.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sensorium.api.dto.request.RoleDto;
import com.sensorium.api.service.impl.UserService;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {

	@Autowired
	private UserService service;

	@Secured("ROLE_ADMIN")
	@GetMapping
	public ResponseEntity<?> index(@RequestParam(defaultValue = "1", name = "page") Integer page) {
		return ResponseEntity.ok(service.getAll(page));
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/{id}/roles/assigne")
	public ResponseEntity<?> assignRole(@PathVariable String id, @RequestBody @Valid RoleDto dto) {
		return ResponseEntity.ok(service.assignRole(id, dto));
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/{id}/roles/unassigne")
	public ResponseEntity<?> unassignRole(@PathVariable String id, @RequestBody @Valid RoleDto dto) {
		return ResponseEntity.ok(service.unassignRole(id, dto));
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/status/{id}")
	public ResponseEntity<?> index(@PathVariable String id) {
		return ResponseEntity.ok(service.getStatus(id));
	}
}
