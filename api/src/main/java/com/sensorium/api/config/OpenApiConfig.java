package com.sensorium.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI sensoriumOpenAPI() {
		return new OpenAPI().info(new Info().title("Sesorium API Documentation").description(
				"This is a secure REST API for managing an IoT sensor infrastructure, developed using Spring Boot. The platform allows for comprehensive management of IoT devices, measurements, and alerts with role-based access control.")
				.version("1.0.0")
				.contact(new Contact().name("BBM").url("https://github.com/Benfill/Sensorium")
						.email("benfillanass@gmail.com"))
				.license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html")));
	}
}