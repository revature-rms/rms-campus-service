package com.revature.rms.campus.services;

import com.revature.rms.campus.entities.Campus;
import com.revature.rms.campus.repositories.CampusMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampusService {

    public CampusMongoRepository cMongoRepo;

    @Autowired
    public CampusService (CampusMongoRepository repo) {
        super();
        this.cMongoRepo = repo;
    }

    public List<Campus> findAll() { return cMongoRepo.findAll(); }

    public Campus findByName(String name) { return cMongoRepo.findByName(name); }

    public Optional <Campus> findById(Integer id) { return cMongoRepo.findById(id); }

    public Campus register(Campus campus) { return cMongoRepo.save(campus); }

    public void deleteById(Integer id) { cMongoRepo.deleteById(id); }


}
