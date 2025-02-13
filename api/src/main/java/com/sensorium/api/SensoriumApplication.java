package com.sensorium.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SensoriumApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensoriumApplication.class, args);
	}

}
