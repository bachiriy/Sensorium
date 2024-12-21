package com.sensorium.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sensorium.api.entity.Device;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {

	List<Device> findByZoneId(String zoneId, Pageable pageable);

}
