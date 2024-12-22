package com.sensorium.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sensorium.api.dto.request.MeasureDtoReq;
import com.sensorium.api.entity.Alert;
import com.sensorium.api.entity.Device;
import com.sensorium.api.entity.enums.DeviceType;
import com.sensorium.api.entity.enums.Severity;
import com.sensorium.api.repository.AlertRepository;
import com.sensorium.api.repository.DeviceRepository;
import com.sensorium.api.repository.MeasureRepository;
import com.sensorium.api.service.impl.DeviceService;
import com.sensorium.api.service.impl.MeasureService;

public class AlertTest {

	@Mock
	private Device mockDevice;

	@InjectMocks
	private MeasureService measureService;

	@Mock
	private MeasureRepository measureRepository;

	@Mock
	private AlertRepository alertRepository;

	@Mock
	private DeviceService deviceService;

	@Mock
	private DeviceRepository deviceRepository;

	@Mock
	private MeasureDtoReq measureDtoReq;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testTemperatureAlertCritical() {
		Alert alert = new Alert(DeviceType.TEMPERATURE_SENSOR, 45.0);
		assertEquals(Severity.CRITICAL, alert.getSeverity());
		assertEquals("Immediate risk for equipment", alert.getMessage());
	}

	@Test
	void testTemperatureAlertHigh() {
		Alert alert = new Alert(DeviceType.TEMPERATURE_SENSOR, 38.0);
		assertEquals(Severity.HIGH, alert.getSeverity());
		assertEquals("Worrying situation requiring rapid action", alert.getMessage());
	}

	@Test
	void testTemperatureAlertNormal() {
		Alert alert = new Alert(DeviceType.TEMPERATURE_SENSOR, 22.0);
		assertEquals(Severity.NORMAL, alert.getSeverity());
		assertEquals("Temperature in the optimal range", alert.getMessage());
	}

	@Test
	void testHumidityAlertCritical() {
		Alert alert = new Alert(DeviceType.HUMIDITY_SENSOR, 0.1);
		assertEquals(Severity.CRITICAL, alert.getSeverity());
		assertEquals("Risk of material damage", alert.getMessage());
	}

	@Test
	void testHumidityAlertNormal() {
		Alert alert = new Alert(DeviceType.HUMIDITY_SENSOR, 0.5);
		assertEquals(Severity.NORMAL, alert.getSeverity());
		assertEquals("Humidity in the optimal range", alert.getMessage());
	}
}
