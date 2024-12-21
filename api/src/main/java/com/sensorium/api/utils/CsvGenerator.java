package com.sensorium.api.utils;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sensorium.api.entity.Measure;

@Component
public class CsvGenerator {

	private static final String CSV_HEADER = "Measure Id, Device Name, Device Type, Value, Timestamp\n";

	public byte[] generateCsv(List<Measure> measures) {
		StringBuilder csvContent = new StringBuilder();

		csvContent.append(CSV_HEADER);

		measures.forEach(m -> {

			csvContent.append(m.getId()).append(", ").append(m.getDevice().getName()).append(", ")
					.append(m.getDevice().getType()).append(", ").append(m.getValue()).append(", ")
					.append(m.getTimestamp()).append("\n");

		});

		return csvContent.toString().getBytes(StandardCharsets.UTF_8);
	}
}
