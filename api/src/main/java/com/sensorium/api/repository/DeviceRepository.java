package com.sensorium.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sensorium.api.entity.Device;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {

}
