package com.sensorium.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sensorium.api.entity.Zone;

public interface ZoneRepository extends MongoRepository<Zone, String> {

}
