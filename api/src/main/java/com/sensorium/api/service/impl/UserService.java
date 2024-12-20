package com.sensorium.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensorium.api.dto.request.RoleDto;
import com.sensorium.api.dto.response.ResponseBody;
import com.sensorium.api.dto.response.UserDtoResp;
import com.sensorium.api.entity.Role;
import com.sensorium.api.entity.User;
import com.sensorium.api.exception.ResourceNotFoundException;
import com.sensorium.api.mapper.UserMapper;
import com.sensorium.api.repository.RoleRepository;
import com.sensorium.api.repository.UserRepository;
import com.sensorium.api.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserMapper mapper;

	@Override
	public User getById(String id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	@Override
	public List<UserDtoResp> getAll(Integer page) {
		int size = 3;
		Pageable pageable = PageRequest.of(page, size);
		List<User> users = repository.findAll(pageable).getContent();
		return mapper.entitiesToDtos(users);
	}

	@Override
	public ResponseBody assignRole(String id, RoleDto dto) {
		User user = getById(id);
		Role role = roleRepository.findByName("ROLE_" + dto.getRole().toUpperCase())
				.orElseThrow(() -> new ResourceNotFoundException("Role not found"));

		Optional<Role> roleOptional = user.getRoles().stream().filter(r -> r.getName().equals(role.getName()))
				.findFirst();
		if (roleOptional.isPresent()) {
			throw new RuntimeException("user already assigned with " + dto.getRole() + " role");
		}

		user.getRoles().add(role);
		repository.save(user);

		return ResponseBody.builder().message("User assigned with " + dto.getRole() + " role successfully").build();
	}

	@Override
	public ResponseBody unassignRole(String id, RoleDto dto) {
		User user = getById(id);
		Role role = roleRepository.findByName("ROLE_" + dto.getRole().toUpperCase())
				.orElseThrow(() -> new ResourceNotFoundException("Role not found"));

		Optional<Role> roleOptional = user.getRoles().stream().filter(r -> r.getName().equals(role.getName()))
				.findFirst();
		if (!roleOptional.isPresent()) {
			throw new RuntimeException("user is not assigned with " + dto.getRole() + " role");
		}

		user.getRoles().remove(role);
		repository.save(user);
		return ResponseBody.builder().message("User assigned with " + dto.getRole() + " role successfully").build();
	}

}
