package com.revature.rms.campus.services;

import com.revature.rms.campus.entities.Room;
import com.revature.rms.campus.entities.RoomStatus;
import com.revature.rms.campus.exceptions.InvalidInputException;
import com.revature.rms.campus.exceptions.ResourceNotFoundException;
import com.revature.rms.campus.repositories.RoomRepository;
import com.revature.rms.campus.repositories.RoomStatusRepository;
import com.revature.rms.core.metadata.*;
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
public class RoomServiceTest {

    @Mock
    RoomRepository repo;
    @Mock
    RoomStatusRepository roomStatusRepository;

    @InjectMocks
    RoomService sut;

    /**
     * testSaveWithValidRoom() ensures RoonService.save() is functioning properly with the valid or correct input.
     * A non-null room object should be returned.
     */
    @Test
    public void testSaveWithValidRoom(){
        Room testRoom = new Room("2301", 25,  new ArrayList<RoomStatus>(5),
                1612, new ArrayList<Integer>(3), new ResourceMetadata( 1,"3.16.2020 10:00 PM", 1, "3.16.2020 10:00 PM", 1, true));

        Room expectedResult = new Room(1,"2301", 25,  new ArrayList<RoomStatus>(5),
                1612, new ArrayList<Integer>(3), new ResourceMetadata( 1,"3.16.2020 10:00 PM", 1, "3.16.2020 10:00 PM", 1, true));

        when(repo.save(Mockito.any())).thenReturn(expectedResult);

        Room actualResults = sut.save(testRoom);

        assertEquals(actualResults, expectedResult);
    }

    /**
     * testSaveWithNullRoom() ensures RoomService.save() will not work if a null room object is being saved. If the room
     * object is null, a ResourceNotFoundException will be thrown since the method does not meet the desired criteria
     * for the method to execute properly.
     */
    @Test(expected = ResourceNotFoundException.class)
    public void testSaveWithNullRoom(){
        //Arrange
        Room testRoom = new Room("2301", 25,  new ArrayList<RoomStatus>(5),
                1612, new ArrayList<Integer>(3), new ResourceMetadata());

        Room expectedResult = new Room(1,"2301", 25,  new ArrayList<RoomStatus>(5),
                1612, new ArrayList<Integer>(3), new ResourceMetadata());
        //Act
        Room actualResult = sut.save(null);

        //Assert
        assertEquals(actualResult, expectedResult);
    }

    /**
     * testFindAll() ensures RoomService().findAll() returns a list of all the existing Room objects.
     */
    @Test
    public void testFindAll(){
        Room testRoom = new Room("2301", 25,  new ArrayList<RoomStatus>(5),
                1612, new ArrayList<Integer>(3), new ResourceMetadata());

        List<Room> mockRoomList = new ArrayList<>();
        when(repo.findAll()).thenReturn(mockRoomList);
        assertEquals(mockRoomList, sut.findAll());
    }

    /**
     * testFindAllRoomWithNoRoom() returns an empty list rather than returning null, thus avoiding a potential
     * NullPointerException in the future.
     */
    @Test
    public void testFindAllRoomWithNoRoom(){
        List<Room> mockRoomList = Collections.emptyList();
        when(repo.findAll()).thenReturn(mockRoomList);
        assertEquals(mockRoomList, sut.findAll());
    }

    /**
     * testFindByRoomNumberWithValidRoomNumber() returns the object containing the same room number as the one provided.
     */
    @Test
    public void testFindByRoomNumberWithValidRoomNumber(){
        Optional<Room> expectedResult = Optional.of(new Room(23,"2304", 25,  new ArrayList<RoomStatus>(5),
                1612, new ArrayList<Integer>(3), new ResourceMetadata()));

        when(repo.findByRoomNumber(Mockito.any())).thenReturn(expectedResult);
        Optional<Room> actualResult = sut.findByRoomNumber("2304");
        assertEquals(actualResult, expectedResult);
    }

    /**
     * testFindByInvalidRoomNumber ensures that findByRoomNumber throws an
     * InvalidInputException when an invalid ID is entered.
     */
    @Test (expected = InvalidInputException.class)
    public void testFindByInvalidRoomNumber() {
        Optional<Room> expectedResult = Optional.of(new Room(23,"2304", 25,  new ArrayList<RoomStatus>(5),
                1612, new ArrayList<Integer>(3), new ResourceMetadata()));

        Optional<Room> actualResult = sut.findByRoomNumber("-100");
    }

    /**
     * findByRoomNumberWithValidRoomNumberNotFound() throws a ResourceNotFoundException when provided a room number
     * that is a correct value but is not associated with room numbers.
     */
    @Test(expected = ResourceNotFoundException.class)
    public void findByRoomNumberWithValidRoomNumberNotFound(){
        when(repo.findByRoomNumber(Mockito.any())).thenReturn(Optional.empty());
        sut.findByRoomNumber("5");
    }

    /**
     * testFindByResourceOwner returns a list of Room objects where the resourceOwner's ID
     * matches the int specified in the parameter of findByResourceOwner.
     */
    @Test
    public void testFindByResourceOwner() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(23,"2304", 25,  new ArrayList<RoomStatus>(5),
                1612, new ArrayList<Integer>(3), new ResourceMetadata()));

        when(repo.findAll()).thenReturn(rooms);
        assertEquals(rooms, sut.findByResourceOwner(1));

    }

    /**
     * testFindByResourceOwnerInvalidId returns an InvalidInputException when
     * the ID is invalid.
     */
    @Test (expected = InvalidInputException.class)
    public void testFindByResourceOwnerInvalidId() {
        sut.findByResourceOwner(-10);
    }

    /**
     * testFindByIdWithValidId() ensures RoomService.findById() returns the object containing the same id as the one provided.
     */
    @Test
    public void testFindByIdWithValidId(){
        Optional<Room> expectedResult = Optional.of(new Room(23,"2304", 25,  new ArrayList<RoomStatus>(5),
                1612, new ArrayList<Integer>(3), new ResourceMetadata()));

        when(repo.findById(Mockito.any())).thenReturn(expectedResult);
        Optional<Room> actualResult = sut.findById(23);
        assertEquals(actualResult, expectedResult);
    }

    /**
     * testFindByIdWithValidIdNotFound() throws a ResourceNotFoundException when provided a correct id but,
     * there is no room associated with it.
     */
    @Test(expected = ResourceNotFoundException.class)
    public void testFindByIdWithValidIdNotFound(){
     when(repo.findById(Mockito.any())).thenReturn(Optional.empty());
     sut.findById(91);
    }

    /**
     * findByIdWithInvalidId() throws an InvalidInputException when the provided id is less than or equal to zero or
     * empty, that is the provided field is empty.
     */
    @Test(expected = InvalidInputException.class)
    public void findByIdWithInvalidId(){
        sut.findById(0); sut.findById(0);
        verify(repo.findById(0), times(0));
    }

    /**
     * testFindMaxOccupancyRooms() returns a list of room objects whose occupancy is equal to the provided number of occupancy.
     */
    @Test
    public void testFindMaxOccupancyRooms(){
        Room testRoom = new Room("2301", 25,  new ArrayList<RoomStatus>(5),
                1612, new ArrayList<Integer>(3), new ResourceMetadata());

        List<Room> occupancyRoomList = new ArrayList<>();
        when(repo.findByMaxOccupancy(Mockito.anyInt())).thenReturn(occupancyRoomList);
        assertEquals(occupancyRoomList, sut.findByMaxOccupancy(35));
    }

    /**
     * testUpdateWithValidRoom() passes in a room object with changed fields to the roomMongoRepository.save() to
     * persist the changes and return the updated saved room object. There is no need for a null room check since the
     * object already exists.
     */
    @Test
    public void testUpdateWithValidRoom(){
        Room testRoom = new Room(1,"2301", 25,  new ArrayList<RoomStatus>(5),
                1612, new ArrayList<Integer>(3), new ResourceMetadata());

        Room expectedResult = new Room("2301", 25,  new ArrayList<RoomStatus>(5),
                1612, new ArrayList<Integer>(3), new ResourceMetadata());

        when(repo.findById(testRoom.getId())).thenReturn(Optional.of(expectedResult));
        when(repo.save(Mockito.any())).thenReturn(expectedResult);

        testRoom.setRoomNumber("2302"); // was 2301

        assertEquals(sut.update(testRoom), expectedResult);
    }

    /**
     * Soft Delete was implemented in RoomService. This was so we can archive data, and not permanently or hard delete data.
     *
     * testDeleteWithValidId() returns an updated room object. This method retrieves the specific room object byt its id,
     * then it sets the active field to false. The changed room object is saved or updated and returned to us.
     */
    @Test
    @Ignore
    public void testDeleteWithValidId(){
        Room testRoom = new Room(1,"2301", 25,  new ArrayList<RoomStatus>(5),
                1612, new ArrayList<Integer>(3), new ResourceMetadata());

        when(repo.findById(1)).thenReturn(Optional.of(testRoom));

        assertEquals(testRoom, sut.delete(1));
    }

    /**
     * testDeleteWithInvalidId() ensures that roomMongoRepository.deleteById() is not run when provided an invalid id.
     * Instead it throws an InvalidInputException.
     */
    @Test(expected = InvalidInputException.class)
    public void testDeleteWithInvalidId(){
        Room testRoom = new Room("2301", 25,  new ArrayList<RoomStatus>(5),
                1612, new ArrayList<Integer>(3), new ResourceMetadata());

        sut.delete(-1);
        verify(repo, times(1)).deleteById(-1);
    }

    /**
     * testFindAllStatusBySubmitter ensures that findAllStatusBySubmitter returns a list of RoomStatus objects
     */
    @Test
    public void testFindAllStatusBySubmitter() {
        List<RoomStatus> roomStatuses = new ArrayList<>();
        when(roomStatusRepository.findAllBySubmitterId(1)).thenReturn(roomStatuses);
        assertEquals(roomStatuses, sut.findAllStatusBySubmitter(1));
    }

    /**
     * testFindAllStatusByDate ensures that findAllStatusByDate returns a list of RoomStatus objects
     */
    @Test
    public void testFindAllStatusByDate() {
        List<RoomStatus> roomStatuses = new ArrayList<>();
        String date = "1/6/2020";
        when(roomStatusRepository.findAllBySubmittedDateTime(date)).thenReturn(roomStatuses);
        assertEquals(roomStatuses, sut.findAllStatusByDate(date));
    }

    /**
     * testFindStatusById ensures that findStatusById returns an Optional of
     * a RoomStatus object.
     */
    @Test
    public void testFindStatusById() {

        Optional<RoomStatus> _roomStatus = Optional.of(new RoomStatus());
        when(roomStatusRepository.findById(1)).thenReturn(_roomStatus);
        assertEquals(_roomStatus, sut.findStatusById(1));
    }

    /**
     * testFindAllStatus ensures that findAllStatus returns a list of
     * RoomStatus objects.
     */
    @Test
    public void testFindAllStatus() {
        List<RoomStatus> roomStatuses = new ArrayList<>();
        when(roomStatusRepository.findAll()).thenReturn(roomStatuses);
        assertEquals(roomStatuses, sut.findAllStatus());
    }

}
