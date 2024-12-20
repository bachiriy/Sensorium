package com.sensorium.api.utils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sensorium.api.entity.Role;
import com.sensorium.api.entity.User;
import com.sensorium.api.entity.enums.RoleEnum;
import com.sensorium.api.repository.RoleRepository;
import com.sensorium.api.repository.UserRepository;

@Component
public class AdminAndRolesSeeder implements ApplicationRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	private Set<Role> roles = new HashSet<Role>();

	@Override
	public void run(ApplicationArguments args) throws Exception {
		createRoles();
		createAdmin();
	}

	private void createRoles() {
		Optional<Role> adminOptional = roleRepository.findByName(RoleEnum.ROLE_ADMIN.toString());
		Optional<Role> userOptional = roleRepository.findByName(RoleEnum.ROLE_USER.toString());

		if (!adminOptional.isPresent()) {
			Role admin = Role.builder().name(RoleEnum.ROLE_ADMIN).build();
			roles.add(roleRepository.save(admin));
		} else {
			roles.add(adminOptional.get());
		}

		if (!userOptional.isPresent()) {
			Role user = Role.builder().name(RoleEnum.ROLE_USER).build();
			roles.add(roleRepository.save(user));
		} else {
			roles.add(userOptional.get());
		}

	}

	private void createAdmin() {
		Optional<User> optionalUser = userRepository.findByUsername("admin");

		if (!optionalUser.isPresent()) {
			String encodedPass = encoder.encode("password");
			User admin = User.builder().name("admin").username("admin").password(encodedPass).roles(roles).enable(true)
					.build();
			userRepository.save(admin);
		}
	}

}
