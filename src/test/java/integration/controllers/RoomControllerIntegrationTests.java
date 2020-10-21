package integration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.rms.campus.CampusServiceApplication;
import com.revature.rms.campus.DTO.RoomDTO;
import com.revature.rms.campus.entities.*;
import com.revature.rms.campus.services.RoomService;
import com.revature.rms.core.exceptions.InvalidRequestException;
import com.revature.rms.core.metadata.ResourceMetadata;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Below are some integration tests for the RoomController. The methods tested in this suite are:
 * - getAllRooms
 * - saveRoom
 * - getRoomById
 * - updateRoom
 * - deleteRoomById
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CampusServiceApplication.class)
@AutoConfigureMockMvc
public class RoomControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RoomService roomService;

    // Declare mock data
    Address testAddress;
    Amenity testAmenity;
    Room testRoom;
    List<Room> testRoomList;
    RoomDTO testRoomDTO;
    RoomStatus testRoomStatus;
    List<RoomStatus> testRoomStatusList;
    List<Integer> testWorkOrders;
    ResourceMetadata testResourceMetadata;

    ObjectMapper mapper;

    // Instantiate mock data
    @Before
    public void setUp(){

        mapper = new ObjectMapper();
        testAddress = new Address(1, "street", "city", "state", "zip", "country");
        testAmenity = new Amenity(1, AmenityType.CLEANING_WIPES, AmenityStatus.OK);
        testRoomStatus = new RoomStatus(1, true, true, "1/1/20", 1, "hey");
        testRoomStatusList = new ArrayList<>();
        testRoomStatusList.add(testRoomStatus);
        testResourceMetadata = new ResourceMetadata(1, "1/1/20", 1, "1/1/20", 1, true);
        testRoom = new Room(1, "105", 24, 1);
        testRoomList = new ArrayList<>();
        testRoomList.add(testRoom);
        testRoomDTO = new RoomDTO(1, "105", 24, true, testRoomStatusList, 1, testWorkOrders, testResourceMetadata);

    }

    /**
     * The method below was created to transform the object passed into a string to satisfy the requirements of
     * MockMvcRequestBuilders methods.
     * @param obj
     * @return String
     */
    public static String asJSON(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This ensures RoomController.getAllRooms hits the desired endpoint provided, produces a JSON object retrieved from
     * the database, and returns a status of 200 if everything was successful.
     * @throws Exception from perform()
     */
    @Test
    public void testGetAllRooms() throws Exception {

        this.mvc.perform(get("/campuses/rooms").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    public void testGetRoomById() throws Exception{

        Mockito.when(roomService.findById(1)).thenReturn(Optional.of(testRoom));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/campuses/rooms/id/1")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

    }

    @Test
    public void testGetRoomByOwnerId() throws Exception {

        Mockito.when(roomService.findByResourceOwner(1)).thenReturn(testRoomList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/campuses/rooms/owners/id/1")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)));

    }

    @Test
    public void testUpdateRoom() throws Exception {

        Mockito.when(roomService.update(testRoom)).thenReturn(testRoom);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/campuses/rooms")
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(testRoomDTO))
                .contentType(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

    }

    @Test
    public void testUpdateRoomNumber() throws Exception {

        Mockito.when(roomService.updateRoomNumber(testRoom)).thenReturn(testRoom);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/campuses/rooms/room-number")
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(testRoomDTO))
                .contentType(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

    }

    @Test
    public void testDeleteRoom() throws Exception {

        Mockito.when(roomService.delete(1)).thenReturn(testRoom);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/campuses/rooms/id/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(testRoomDTO))
                .contentType(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder).andExpect(status().isNoContent());

    }

}
