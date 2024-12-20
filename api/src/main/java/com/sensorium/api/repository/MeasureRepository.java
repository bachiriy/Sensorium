package com.sensorium.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sensorium.api.entity.Measure;

@Repository
public interface MeasureRepository extends MongoRepository<Measure, String> {

}
