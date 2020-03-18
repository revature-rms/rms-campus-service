package com.revature.rms.campus.controllers;

import com.revature.rms.campus.entities.Building;
import com.revature.rms.campus.entities.Campus;
import com.revature.rms.campus.exceptions.InvalidInputException;
import com.revature.rms.campus.exceptions.ResourceNotFoundException;
import com.revature.rms.campus.services.CampusService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Optional;

/**
 * Rest controller for the CampusService. All of the methods below either produce or produce and consume JSON values
 * The methods provide validations through the result of TDD in CampusServiceTests and CampusControllerTests.
 * Methods include:
 * - getAllCampus, this method performs a get with the url in the RequestMapping annotation and returns the result
 * of campusService.findAll() as a list of JSON campus objects.
 * - saveCampus, this method performs a post with provided url in the RequestMapping annotation. Expected the provided
 * information to be a JSON and returns the JSON of campusService.save()
 * - getCampusById, this method performs a get with the url in the RequestMapping annotation and including the provided
 * param. Returns the result of campusService.findById() as a JSON.
 * - updateCampus this method performs a put with the provided url and returns the result of campusService.update().
 * - deleteCampusById, this method performs a get with the url in the RequestMapping annotation and including the provided
 *  * param. Returns the result of campusService.delete() as a JSON.
 */
@RestController
@RequestMapping("/v2/campus")
public class CampusController {

    private CampusService campusService;

    @Autowired
    public CampusController(CampusService campusService) {
        this.campusService = campusService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Campus> getAllCampus() { return campusService.findAll(); }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Campus saveCampus(@RequestBody Campus campus) {
        if (campus == null) {
            throw new ResourceNotFoundException();
        }
        return campusService.save(campus);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Campus getCampusById(@PathVariable String id) {
        if (id.isEmpty() || Integer.parseInt(id) <= 0) {
            throw new InvalidInputException();
        }
        Optional<Campus> _campus = campusService.findById(id);
        if (!_campus.isPresent()) {
            throw new ResourceNotFoundException();
        }
        return campusService.findById(id).get();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Campus updateCampus(@RequestBody Campus campus) { return campusService.update(campus); }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCampusById(@PathVariable String id) {
        if(id.isEmpty() || Integer.parseInt(id) <= 0) {
            throw new InvalidInputException();
        }
        campusService.delete(id);}
}
