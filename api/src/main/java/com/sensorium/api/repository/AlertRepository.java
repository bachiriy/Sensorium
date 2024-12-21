package com.sensorium.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sensorium.api.entity.Alert;

@Repository
public interface AlertRepository extends MongoRepository<Alert, String> {

	List<Alert> findByDeviceId(String deviceId);

}
