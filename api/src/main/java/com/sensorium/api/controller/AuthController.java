package com.sensorium.api.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sensorium.api.dto.request.LoginReq;
import com.sensorium.api.dto.request.UserDtoReq;
import com.sensorium.api.dto.response.ResponseBody;
import com.sensorium.api.service.impl.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService service;

	@Value("${jwt.header.string}")
	private String HEADER_STRING;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginReq dto, HttpServletResponse response) {
		return ResponseEntity.ok(service.login(dto, response));
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid UserDtoReq dto) {
		return ResponseEntity.ok(service.register(dto));
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletResponse response, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String message = "Something went wrong";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (HEADER_STRING.equals(cookie.getName())) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
					message = "User logged out successfully";
				}
			}
		} else {
			message = "User is not logged in";
		}

		return ResponseEntity.ok(new ResponseBody(message));
	}

}
