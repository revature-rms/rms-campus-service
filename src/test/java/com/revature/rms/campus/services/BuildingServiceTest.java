package com.revature.rms.campus.services;
import com.revature.rms.campus.entities.*;
import com.revature.rms.core.metadata.*;
import com.revature.rms.campus.exceptions.InvalidInputException;
import com.revature.rms.campus.exceptions.ResourceNotFoundException;
import com.revature.rms.campus.exceptions.ResourcePersistenceException;
import com.revature.rms.campus.repositories.BuildingRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BuildingServiceTest {
    @Mock
    BuildingRepository repo;
    @InjectMocks
    BuildingService sut;

    /**
     * testSaveWithValidBuilding() ensures BuildingService.save() is functioning properly with the valid or correct input.
     * A non-null building object should be returned.
     */
    @Test
    @Ignore
    public void testSaveWithValidBuilding() {

        Building testBuilding = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());
        Building expectedResult = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());

        when(repo.save(Mockito.any())).thenReturn(expectedResult);

        Building actualResults = sut.save(testBuilding);

        assertEquals(actualResults, expectedResult);
    }

    /**
     * testSaveWithNullBuilding() ensures BuildingService.save() will not work if a null building object is being saved.
     * If the building object is null, a ResourceNotFoundException will be thrown since the method does not meet the
     * desired criteria for the method to execute properly.
     */
    @Test(expected = ResourcePersistenceException.class)
    public void testSaveWithNullBuilding(){

        Building testBuilding = new Building("Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());

        Building expectedResult = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());

        Building actualResults = sut.save(null);

    }

    /**
     * testFindAll() ensures BuildingService().findAll() returns a list of all the existing Building objects.
     */
    @Test
    public void testFindAll() {
         Building testBuilding = new Building(1, "Muma School of Business", "MSB", new Address(),
                 2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());
        List<Building> mockBuildingList = Arrays.asList(testBuilding);
        when(repo.findAll()).thenReturn(mockBuildingList);
        assertEquals(mockBuildingList, sut.findAll());
    }

    /**
     * testFindAllBuildingWithNoCampus() returns an empty list rather than returning null, thus avoiding a potential
     * NullPointerException in the future.
     */
    @Test
    public void testFindAllBuildingWithNoCampus() {
        List<Building> mockBuildingList = Collections.emptyList();
        when(repo.findAll()).thenReturn(mockBuildingList);
        assertEquals(mockBuildingList, sut.findAll());
    }

    /**
     * testFindBuildingByIdWithValidId() ensures BuildingService.findById() returns the object containing the same id as the one provided.
     */
    @Test
    public void testFindBuildingByIdWithValidId() {
        Optional<Building> expectedResult = Optional.of(new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata()));

        when(repo.findById(Mockito.any())).thenReturn(expectedResult);
        Optional<Building> actualResult = sut.findById(1);
        assertEquals(actualResult, expectedResult);
    }

    /**
     * testFindBuildingWithValidIdNotFound() throws a ResourceNotFoundException when provided a correct id but,
     * there is no building associated with it.
     */
    @Test(expected = ResourceNotFoundException.class)
    public void testFindBuildingWithValidIdNotFound() {
        when(repo.findById(Mockito.any())).thenReturn(Optional.empty());
        sut.findById(1);
    }

    /**
     * testFindBuildingWithInvalidId() throws an InvalidInputException when the provided id is less than or equal to zero or
     * empty, that is the provided field is empty.
     */
    @Test(expected = InvalidInputException.class)
    public void testFindBuildingWithInvalidId() {
        sut.findById(0); sut.findById(0);
        verify(repo.findById(0), times(0));
    }

    /**
     * testFindBuildingWithValidName() returns the object containing the same building name as the one provided.
     */
    @Test
    public void testFindBuildingWithValidName() {
        String name = "Muma School of Business";
        Building expectedResult = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());

        when(repo.findByName(Mockito.any())).thenReturn(expectedResult);
        Building actualResult = sut.findByName(name);

        assertEquals(expectedResult, actualResult);
    }

    /**
     * testFindBuildingWithNullName returns InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void testFindBuildingWithNullName() {
        sut.findByName(null);
    }

    /**
     * testFindAllBuildingsByBuildingOwnerId asserts that the result of findAllBuildingsByOwnerId in
     * the service class returns the expected list of Building objects.
     */
    @Test
    public void testFindAllBuildingsByOwnerId() {

        List<Building> expectedResult = new ArrayList<>();
        expectedResult.add(new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata()));

        when(repo.findAll()).thenReturn(expectedResult);
        List<Building> actualResult = sut.findAllBuildingsByOwnerId(1);

        assertEquals(expectedResult, actualResult);
    }

    /**
     * testFindAllBuildingsByInvalidOwnerId tests that an ID less than 1 returns
     * an InvalidInputException.
     */
    @Test(expected = InvalidInputException.class)
    public void testFindAllBuildingsByInvalidOwnerId() {
        sut.findAllBuildingsByOwnerId(-2);
    }

    /**
     * testFindAllBuildingsByOwnerIdEmptyList tests that when the service tries to
     * return an empty list of Building objects, a ResourceNotFoundException occurs.
     */
    @Test(expected = ResourceNotFoundException.class)
    public void testFindAllBuildingsByOwnerIdEmptyList() {
        // setup empty list
        List<Building> expectedResult = new ArrayList<>();

        when(repo.findAll()).thenReturn(expectedResult);
        List<Building> actualResult = sut.findAllBuildingsByOwnerId(1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testUpdateBuilding() {
        Building building = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());
        sut.save(building);
        Building updatedBuilding = new Building("Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());
        when(repo.save(updatedBuilding)).thenReturn(updatedBuilding);
        assertEquals(sut.update(updatedBuilding), updatedBuilding);
    }

    @Test(expected = InvalidInputException.class)
    public void testUpdateWithNullBuilding() {
        sut.update(null);
    }

    /**
     * testFindBuildingByTrainingLead() returns an object containing the same trainer id as the one provided.
     */
    @Test
    public void testFindBuildingByTrainingLead() {
        Building expectedResult = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());

        when(repo.findByTrainingLead(Mockito.any())).thenReturn(expectedResult);
        Building actualResult = sut.findByTrainingLeadId(2);

        assertEquals(expectedResult, actualResult);
    }

    /**
     * testFindBuildingByTrainingLeadInvalidId() throws an InvalidInputException when the provided id is less than or equal to zero.
     */
    @Test(expected = InvalidInputException.class)
    public void testFindBuildingByTrainingLeadInvalidId() {
        Building actualResult = sut.findByTrainingLeadId(0);
    }

    /**
     * testFindBuildingByTrainingLeadIdNotPresent() throws a ResourceNotFoundException when provided a trainer id
     * that is a correct value but is not associated with any buildings.
     */
    @Test(expected = ResourceNotFoundException.class)
    public void testFindBuildingByTrainingLeadIdNotPresent() {
        when(repo.findByTrainingLead(Mockito.any())).thenReturn(null);
        Building actualResult = sut.findByTrainingLeadId(100);
    }

    /**
     * testFindBuildingWithValidNameUsingAbbreviatedName() returns the object containing the same abbreviated building name
     * as the one provided.
     */
    @Test
    public void testFindBuildingWithValidNameUsingAbbreviatedName() {
        String name = "MSB";
        Building expectedResult = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());

        when(repo.findByName(Mockito.any())).thenReturn(expectedResult);
        Building actualResult = sut.findByName(name);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * testUpdateWithValidBuilding() passes in a building object with changed fields to the buildingMongoRepository.save() to
     * persist the changes and return the updated saved building object. There is no need for a null building check since the
     * object already exists.
     */
    @Test
    public void testUpdateWithValidBuilding() {
        Building testBuilding = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());

        Building expectedResult = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());

        when(repo.save(Mockito.any())).thenReturn((expectedResult));
        Building actualResult = sut.save(testBuilding);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * testDeleteWithValidId() ensures buildingService.delete() functions by verifying buildingMongoRepository.deleteById() is ran
     * successfully one time when provided with a valid id.
     */
    @Test
    public void testDeleteWithValidId() {
        Building testBuilding = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());

        sut.delete(testBuilding.getId());
        verify(repo, times(1)).deleteById(testBuilding.getId());
    }

    /**
     * testDeleteWithInvalidId() ensures that buildingMongoRepository.deleteById() is not run when provided an invalid id.
     * Instead it throws an InvalidInputException.
     */
    @Test(expected = InvalidInputException.class)
    public void testDeleteWithInvalidId() {
        Building testBuilding = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());
        sut.delete(-24);
        verify(repo, times(0)).deleteById(-24);
    }


}
