package com.revature.rms.campus.repositories;

import com.revature.rms.campus.entities.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import com.revature.rms.core.metadata.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
//import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BuildingRepositoryTest {

    @Autowired
    private BuildingRepository sut;

//    @BeforeEach
//    public void setup() throws Exception {
//        Building building1 = new Building("NEC", "NEC", new Address(), 1, null, null, null);
//        Building building2 = new Building("BCN", "BCN", new Address(), 2, null, null, null);
//        //save product, verify has ID value after save
//        this.sut.save(building1);
//        this.sut.save(building2);
//    }
    @AfterEach
    public void tearDown() throws Exception {
        this.sut.deleteAll();
    }

    // Replace ResourceMetaData with the columns ResourceMetaData had. (All except Id) Stephen, if you have any questions let sean know
//    @Test
//    public void testSaveBuilding() {
//        Building building = new Building("Test", "TEST", new Address(1,"","","","",""), 10, null, null, null);
//        sut.save(building);
//
//        Building result = sut.findByTrainingLead(10);
//        assertThat(result).isNotNull();
//
//        assertThat(result.getAbbrName()).isEqualTo("TEST");
//    }

//    @Test
//    public void testFindAll() {
//        Building building = new Building("Test1", "TEST1", new Address(1,"asd","asd","asd","asd","asd"), 12, null, null, null);
//        Building building2 = new Building("Test2", "TEST2", new Address(1,"asdf","asdf","asdf","asdf","asdf"), 13, null, null, null);
//        sut.save(building);
//        sut.save(building2);
//        int originalCount = 0;
//        int testCount = 0;
//        Iterable<Building> buildingList2 = sut.findAll();
//        List<Building> result = new ArrayList<>();
//        for (Building b : buildingList2) {
//            testCount++;
//            result.add(b);
//        }
//        assertThat(4).isEqualTo(result.size());
//
//    }
    @Test
    public void testFindByName() {
        Amenity amenity = new Amenity(1,AmenityType.COFFEE, AmenityStatus.OK);
        ResourceMetadata rmd = new ResourceMetadata();
        List<Amenity> amenities = new ArrayList<>();
        amenities.add(amenity);
        Room room = new Room(1,"200",10,new ArrayList<>(),1,new ArrayList<>(),rmd);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        Building building = new Building("Test3", "TEST3", new Address(1,"asd","asd","asd","asd","asd"), 12, amenities, rooms, rmd);
        sut.save(building);
        Building building1 = sut.findByName("Test3");
        assertNotNull(building1);
        assertEquals( building1.getAbbrName(), building.getAbbrName() );
    }
//    @Test
//    public void testUpdate() {
//        Building building1 = sut.findByName("NEC");
//        assertNotNull(building1);
//        building1.setAbbrName("PogChamp");
//        sut.save(building1);
//        Building building2 = sut.findByName("NEC");
//        assertNotNull(building2);
//        assertEquals("PogChamp", building2.getAbbrName());
//    }
//    @Test
//    public void testDelete() {
//        Building building1 = sut.findByName("NEC");
//        this.sut.delete(building1);
//        Building building2 = sut.findByName("NEC");
//        assertNull(building2);
//    }

}
