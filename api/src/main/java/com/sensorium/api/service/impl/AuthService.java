package com.sensorium.api.service.impl;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sensorium.api.dto.request.LoginReq;
import com.sensorium.api.dto.request.UserDtoReq;
import com.sensorium.api.dto.response.LoginResp;
import com.sensorium.api.dto.response.UserDtoResp;
import com.sensorium.api.entity.Role;
import com.sensorium.api.entity.User;
import com.sensorium.api.entity.enums.RoleEnum;
import com.sensorium.api.exception.ResourceNotFoundException;
import com.sensorium.api.mapper.UserMapper;
import com.sensorium.api.repository.RoleRepository;
import com.sensorium.api.repository.UserRepository;
import com.sensorium.api.security.jwt.TokenProvider;
import com.sensorium.api.service.IAuthService;

@Service
@EnableScheduling
public class AuthService implements IAuthService {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UserMapper mapper;

	@Autowired
	private TokenProvider jwtUtil;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	UserService userService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	TaskScheduler taskScheduler;

	@Override
	public LoginResp login(LoginReq dto, HttpServletResponse response) {
		User savedUser = userRepo.findByUsername(dto.getUsername())
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

		if (savedUser.getEnable() == false) {
			throw new RuntimeException("user is blocked");
		}

		if (!encoder.matches(dto.getPassword(), savedUser.getPassword())) {
			blockUserAfter3tentatives(dto.getUsername());
			throw new RuntimeException("Password is wrong " + savedUser.getId());
		}
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

		org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
				savedUser.getUsername(), savedUser.getPassword(), savedUser.getRoles().stream()
						.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtil.generateToken(userDetails);

		Cookie jwtCookie = new Cookie("Authorization", token);

		jwtCookie.setHttpOnly(true);
		jwtCookie.setSecure(true);
		jwtCookie.setPath("/");
		jwtCookie.setMaxAge(7 * 24 * 60 * 60);

		response.addCookie(jwtCookie);

		// Optionally, send some additional response if needed
		response.setStatus(HttpServletResponse.SC_OK);

		return LoginResp.builder().name(savedUser.getName()).username(savedUser.getUsername())
				.roles(savedUser.getRoles()).enable(savedUser.getEnable()).build();
	}

	private void blockUserAfter3tentatives(String username) {
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		System.out.println("user id: " + user.getId());
		Integer tentatives = user.getTentative() != null ? user.getTentative() : 0;

		if (tentatives == 2) {
			user.setEnable(false);
			user.setTentative(0);
			userService.updateUserTentatives(user);

			System.out.println("user blocked");
			Instant unblockTime = Instant.now().plusSeconds(60);
			System.out.println("waiting 6 sec...");

			taskScheduler.schedule(() -> {
				user.setEnable(true);
				userService.updateUserTentatives(user);
				System.out.println("user unblocked");
			}, unblockTime);

		} else {
			tentatives++;
			System.out.println("user still have chance tentatives:" + tentatives + " " + user.getRoles());
			user.setTentative(tentatives);
			userService.updateUserTentatives(user);
		}

	}

	@Override
	public UserDtoResp register(UserDtoReq dto) {
		User user = mapper.DtoToentity(dto);

		Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER.toString())
				.orElseThrow(() -> new ResourceNotFoundException("Role User not found"));

		Optional<User> existingUser = userRepo.findByUsername(dto.getUsername());
		if (existingUser.isPresent()) {
			throw new RuntimeException("User is already exists");
		}

		Set<Role> roles = new HashSet<Role>();
		roles.add(userRole);
		user.setRoles(roles);
		user.setEnable(true);
		user.setPassword(encoder.encode(dto.getPassword()));

		User savedUser = userRepo.save(user);
		return mapper.entityToDto(savedUser);
	}

}
