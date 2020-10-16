package com.revature.rms.campus.controllers;


import com.revature.rms.campus.entities.Building;
import com.revature.rms.core.exceptions.*;
import com.revature.rms.campus.services.BuildingService;
import dto.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/campuses/buildings") // service name/controller name
public class BuildingController {

    private BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    /**
     * saveBuilding method: Takes in a building object as the input.
     * @param building newly persisted building object
     * @return the newly added building object
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Building saveBuilding(@RequestBody Building building) {
        if (building == null) {
            throw new ResourceNotFoundException();
        }
        return buildingService.save(building);
    }

    /**
     * getAllBuildings method: Returns a list of all the building objects in the database.
     * @return a list of buildings
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Building> getAllBuildings() {
        return buildingService.findAll();
    }

    /**
     * getBuildingById method: Returns a building object when the id int matches a record in the database.
     * @param id buildingId int value
     * @return a building with matching id
     */
    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Building getBuildingById(@PathVariable int id) {
        if (id <= 0) {
            throw new InvalidRequestException();
        }
        Optional<Building> _building = buildingService.findById(id);
        if (!_building.isPresent()) {
            throw new ResourceNotFoundException();
        }
        return buildingService.findById(id).get();
    }

    /**
     * getBuildingByTrainingLeadId method: Returns a building object
     * that matches a trainingLeadId int id.
     * @param id trainingLeadId int value
     * @return a building with matching trainerLeadId
     */
    @GetMapping(value = "/trainers/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Building getBuildingByTrainerId(@PathVariable int id) { return buildingService.findByTrainingLeadId(id); }

    /**
     * getBuildingByOwnerId method: Retrieves a list of Building owned by a specific app user
     * @param id ID of the app user
     * @return List of buildings
     */

    @GetMapping(value = "/owners/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Building> getBuildingByOwnerId(@PathVariable Integer id){
        return buildingService.findAllBuildingsByOwnerId(id);
    }

    /**
     * updateBuilding method: The building object is inputted and changes are saved.
     * @param building newly updated building object
     * @return updated/modified building object
     */

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Building updateBuilding(@RequestBody Building building) {
        return buildingService.update(building);
    }

    /**
     * deleteBuildingById method: The building object is deleted based on its buildingId int
     * @param id buildingId int value
     */
    @DeleteMapping(value = "/id/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteBuildingById(@PathVariable int id) {
        if (id <= 0) {
            throw new InvalidRequestException();
        }
        buildingService.delete(id);
    }

//    /**
//     * handleInvalidRequestException method: Exception handler method that provides the correct
//     * error response based on a InvalidRequestException
//     * @param e InvalidRequestException where input from user is invalid
//     * @return ErrorResponse that provides status, message, and timestamp of the exception
//     */
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse handleInvalidRequestException(InvalidRequestException e) {
//        ErrorResponse err = new ErrorResponse();
//        err.setMessage(e.getMessage());
//        err.setTimestamp("Time now");
//        err.setStatusCode(400);
//        return err;
//    }
//
//    /**
//     * handleResourceNotFoundException method: Exception handler method that provides the correct
//     * error response based on a ResourceNotFoundException
//     * @param e ResourceNotFoundException where a resource is not found in the database
//     * @return ErrorResponse that provides status, message, and timestamp of the exception
//     */
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException e) {
//        ErrorResponse err = new ErrorResponse();
//        err.setMessage(e.getMessage());
//        err.setTimestamp(System.currentTimeMillis());
//        err.setStatus(401);
//        return err;
//    }
}
