package com.revature.rms.campus.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.revature.rms.core.metadata.*;


//TODO: Attempt to implement data into the testing database, needs ActiveProfile to run second DB
//@SpringBootTest(classes = {CampusServiceApplication.class, H2TestProfileJPAConfig.class})
@SpringBootTest
@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
public class RoomRepositoryTest {
//    @Autowired
//    private static RoomStatusRepository statusRepository;
//
//    @Autowired
//    private static ResourceMetadataRepository metadataRepository;

    @Autowired
    private RoomRepository sut;


    @Autowired
    RoomStatusRepository statusRepository;

    //For posterity, needed to insert dummy data into the h2 testing database
    @Before
    public void setUp() {

    }

    @Test
    public void testFindByRoomNumberH2() {
//        Room room1 = new Room(1,"105A", 100, new ArrayList<>(), 1, new ArrayList<>(), new ResourceMetadata());
//        room1.setBuilding(Mockito.mock(Building.class));
//        System.out.println(room1.toString());
//        sut.save(room1);
//        Room result = sut.findByRoomNumber("105A").get();
//        assertThat(result.getMaxOccupancy()).isEqualTo(100);
    }

    //Replace resourceMetaData with the columns ResourceMetaData had. Leave out the Id column. (Stephen, if you have any questions, let sean know)
//    @Test
//    public void testFindByRoomNumber() {
//        Room room1 = new Room(1,"2301D", 45, null, 1, null, null);
//        sut.save(room1);
//        Room expected = sut.findByRoomNumber("2301D").get();
//
//        assertThat(expected.getMaxOccupancy()).isEqualTo(45);
//    }
//
//    @Test
//    public void testFindByMaxOccupancy() {
//        Room room1 = new Room(1,"301D", 45, null, 1, null, null);
//        Room room2 = new Room(2,"2301D", 45, null, 1, null, null);
//        sut.save(room1);
//        sut.save(room2);
//        List<Room> expected = sut.findByMaxOccupancy(45);
//        assertThat(expected.size()).isEqualTo(2);

//    }
//    @Test
//    public void testFindAll() {
//        Iterable<Room> rooms = sut.findAll();
//        int count = 0;
//        for(Room p : rooms) {
//            count++;
//        }
//        assertEquals(19, count);
//    }
//    @Test
//    public void testFindById() {
//        Optional<Room> room1 = sut.findByRoomNumber("2301D");
//        assertTrue(room1.isPresent());
//        Optional<Room> room2 = sut.findById(room1.get().getId());
//        assertTrue(room2.isPresent());
//        assertEquals( room1.get(), room2.get() );
//    }
//    @Test
//    public void testUpdate() {
//        Optional<Room> room1 = sut.findByRoomNumber("2301D");
//        assertTrue(room1.isPresent());
//        room1.get().setActive(false);
//        sut.save(room1.get());
//        Optional<Room> room2 = sut.findByRoomNumber("2301D");
//        assertTrue(room1.isPresent());
//        assertTrue(!room2.get().getActive());
//    }
//    @Test
//    public void testDelete() {
//        Optional<Room> room1 = sut.findByRoomNumber("2301D");
//        assertTrue(room1.isPresent());
//        this.sut.delete(room1.get());
//        Optional<Room> room2 = sut.findByRoomNumber("2301D");
//        assertFalse(room2.isPresent());
//    }
    @AfterEach
    public void tearDown() throws Exception {
        this.sut.deleteAll();
    }
}
