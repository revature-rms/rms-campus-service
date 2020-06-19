package com.revature.rms.campus.repositories;

import com.revature.rms.campus.entities.Campus;
//import org.springframework.data.mongodb.repository.MongoRepository;
import com.revature.rms.campus.entities.ResourceMetadata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampusRepository extends CrudRepository<Campus, Integer> {
    Campus findByName(String name);

    Campus findByTrainingManagerId(Integer id);

    Campus findByStagingManagerId(Integer id);
}