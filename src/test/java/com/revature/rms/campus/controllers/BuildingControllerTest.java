package com.revature.rms.campus.controllers;
import com.revature.rms.campus.entities.*;
import com.revature.rms.core.exceptions.*;
import com.revature.rms.campus.services.BuildingService;
import com.revature.rms.core.metadata.ResourceMetadata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
/**
 * These tests are for additional validation and to ensure the CampusService methods are working successfully when called
 * by the CampusController methods. See CampusServiceTests for more details on testing for CampusService methods.
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class BuildingControllerTest {
    @InjectMocks
    private BuildingController buildingController;

    @Mock
    private BuildingService buildingService;

    @Test
    public void testFindAllBuildingWithValidBuilding() {
        Building testBuilding = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());

        List<Building> testBuildingList = Arrays.asList(testBuilding);

        when(buildingService.findAll()).thenReturn(testBuildingList);

        assertEquals(buildingController.getAllBuildings(), testBuildingList);
    }

    @Test
    public void testFindAllBuildingWithNoBuilding() {

        List<Building> testBuildingList = Collections.emptyList();

        when(buildingService.findAll()).thenReturn(testBuildingList);

        assertEquals(buildingController.getAllBuildings(), testBuildingList);
    }

    @Test
    public void testFindBuildingByTrainingLeadId() {
        int id=2;
        Building testBuilding = new Building("Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());

        when(buildingService.findByTrainingLeadId(Mockito.anyInt())).thenReturn(testBuilding);

        assertEquals(buildingController.getBuildingByTrainerId(id), testBuilding);
    }

    @Test(expected = InvalidRequestException.class)
    public void testFindBuildingByInvalidTrainingLeadId() {
        int id=-2;
        when(buildingService.findByTrainingLeadId(Mockito.anyInt())).thenThrow(new InvalidRequestException());
        buildingController.getBuildingByTrainerId(id);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testFindNullBuildingById() {
        Building building = null;
        when(buildingService.findById(1)).thenReturn(Optional.empty());
        buildingController.getBuildingById(1);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testFindBuildingByTrainingLeadIdWithNullResult() {
        int id=2;
        when(buildingService.findByTrainingLeadId(Mockito.anyInt())).thenThrow(new ResourceNotFoundException());
        buildingController.getBuildingByTrainerId(id);
    }

    @Test
    public void testSaveBuildingWithValidBuilding() {

        Building testBuilding = new Building("Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());

        Building persistedBuilding = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());

        when(buildingService.save(testBuilding)).thenReturn(persistedBuilding);


        assertEquals(buildingController.saveBuilding(testBuilding), persistedBuilding);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testSaveBuildingWithNullBuilding() {

        String id = "1";

        Building expectedResult = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());

        when(buildingService.save(Mockito.any())).thenReturn(expectedResult);
        assertEquals(buildingController.saveBuilding(null), expectedResult);
    }


    @Test
    public void testGetBuildingByValidId() {
        int id = 1;

        Building expectedResult = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(), new ArrayList<Room>(), new ResourceMetadata());

        when(buildingService.findById(1)).thenReturn(Optional.of(expectedResult));
        assertEquals(buildingController.getBuildingById(id), expectedResult);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetBuildingByNotFoundId() {
        int id = 1;

        when(buildingService.findById(1)).thenThrow(new ResourceNotFoundException());

        buildingController.getBuildingById(id);
    }

    @Test(expected = InvalidRequestException.class)
    public void testGetBuildingWithInvalidBuilding() {

        int id = 0;
        when(buildingService.findById(id)).thenReturn(null);

        assertEquals(buildingController.getBuildingById(id), null);
    }

    @Test
    public void testFindAllBuildingsByOwnerId() {
        int id = 1;
        Building expectedResult = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(), new ArrayList<Room>(), new ResourceMetadata());
        List<Building> result = new ArrayList<>();
        result.add(expectedResult);
        when(buildingService.findAllBuildingsByOwnerId(Mockito.anyInt())).thenReturn(result);
        assertEquals(buildingController.getBuildingByOwnerId(id), result);
    }

    @Test(expected = InvalidRequestException.class)
    public void testFindBuildingByInvalidOwnerId() {
        int id = -1;
        Building expectedResult = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(), new ArrayList<Room>(), new ResourceMetadata());
        List<Building> result = new ArrayList<>();
        result.add(expectedResult);
        when(buildingService.findAllBuildingsByOwnerId(Mockito.anyInt())).thenThrow(new InvalidRequestException());
        assertEquals(buildingController.getBuildingByOwnerId(id), result);
    }

    @Test
    public void testUpdateBuildingWithValidBuilding() {
        Building testBuilding = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());

        Building expectedResult = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());

        when(buildingService.update(Mockito.any())).thenReturn(expectedResult);
        assertEquals(buildingController.updateBuilding(testBuilding), expectedResult);
    }

    @Test
    public void testDeleteBuildingWithValidId() {

        int id = 1;

        buildingController.deleteBuildingById(id);
        verify(buildingService, times(1)).delete(id);
    }

    @Test(expected = InvalidRequestException.class)
    public void testDeleteBuildingWithInvalidId() {
        Building testBuilding = new Building(1, "Muma School of Business", "MSB", new Address(),
                2, new ArrayList<Amenity>(1), new ArrayList<Room>(3), new ResourceMetadata());

                int testId = -1;
        when(buildingService.findById(testId)).thenReturn(Optional.of(testBuilding));
        buildingController.deleteBuildingById(testId);
        verify(buildingService, times(0)).delete(testId);
    }
}
