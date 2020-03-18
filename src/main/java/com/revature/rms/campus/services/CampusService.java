package com.revature.rms.campus.services;


import com.revature.rms.campus.entities.Campus;
import com.revature.rms.campus.exceptions.InvalidInputException;
import com.revature.rms.campus.exceptions.ResourceNotFoundException;
import com.revature.rms.campus.repositories.CampusMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The methods in this service call to methods from the campusMongoRepository in order to give the basic CRUD features to
 * the application. The methods in this service are custom as a result of TDD. For more information about the testing
 * see CampusServiceTests.
 * The methods in this service are:
 * - save(), this method returns the result of campusMongoRepository.save when a non-null campus object is passed in.
 * Otherwise this method will throw a ResourceNotFoundException as the desired campus to save is missing. Campus field
 * level validation is handled in entities.Campus
 * - findAll(), this method returns the result of campusMongoRepository.findAll. The method will return a list of Campus
 * objects if there are any existing. Otherwise, it will return an empty list.
 * - findById, this method returns the result of campusMongoRepository.findById. If the provided input is invalid, an
 * empty string or and int less than or equal to 0 the method will thrown an InvalidInputException. Alternatively, if
 * the id is valid but there is no campus object associated with the id, the method will throw a
 * ResourceNotFoundException.
 * - findByName, this method returns the result of campusMongoRepository.findByName. This is a custom method made for
 * proof of concept and thoughts of additional functionality. The method functions similar to findById but instead will
 * return the object using either the name or the abbreviatedName associated with the campus object. Because this method
 * has not been used there is no validation other than testing functionality. If deciding to implement please develop
 * with TDD in the CampusServiceTests.
 * - update, this method returns the result of campusMongoRepository.save. This will update the edited fields for the
 * persisted campus object. Since the object is already persisted when using this method, there is no reason to check
 * if it's null. Additionally field checks are still handled in entities.Campus.
 * - delete, this method uses campusMongoRepository.deleteById. This will delete the persisted object from the database
 * if the user has provided a valid id. If the provided input is invalid, an empty string or and int less than or equal
 * to 0 the method will thrown an InvalidInputException. No exception a valid id but no associated object. Technically,
 * this object does not exist and therefore is not an issue.
 */
@Service
public class CampusService {

    private CampusMongoRepository campusMongoRepository;

    @Autowired
    public CampusService(CampusMongoRepository repo) {
        this.campusMongoRepository = repo;
    }

    public Campus save(Campus campus) {
        if (campus == null) {
            throw new ResourceNotFoundException();
        }
        return campusMongoRepository.save(campus);
    }


    public List<Campus> findAll() {
        return campusMongoRepository.findAll();
    }

    public Optional<Campus> findById(String id) {
        if (id.isEmpty() || (Integer.parseInt(id) <= 0)) {
            throw new InvalidInputException();
        }
        Optional<Campus> _campus = campusMongoRepository.findById(id);
        if (!_campus.isPresent()) {
            throw new  ResourceNotFoundException();
        }
        return _campus;
    }

    public Campus findByName(String name) {
        return campusMongoRepository.findByName(name);
    }

    public Campus update(Campus campus) {
        return campusMongoRepository.save(campus);
    }

    public void delete(String id) {
        if (id.isEmpty() || Integer.parseInt(id) <= 0) {
            throw new InvalidInputException();
        }
        campusMongoRepository.deleteById(id);
    }
}
