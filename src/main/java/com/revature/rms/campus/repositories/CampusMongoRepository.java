package com.revature.rms.campus.repositories;

import com.revature.rms.campus.entities.Address;
import com.revature.rms.campus.entities.Campus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CampusMongoRepository extends MongoRepository<Campus, Integer> {

    Campus findByName(String name);

}
