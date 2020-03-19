package integration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.rms.campus.CampusServiceApplication;
import com.revature.rms.campus.entities.ResourceMetadata;
import com.revature.rms.campus.entities.Room;
import com.revature.rms.campus.entities.RoomStatus;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CampusServiceApplication.class)
@AutoConfigureMockMvc
public class RoomControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    public static String asJSON(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetAllRoomsWithExistingRoomsExpecting200() throws Exception {

        this.mvc.perform(get("/v2/room").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testSaveRoomWithValidRoomExpecting200() throws Exception {
        Room testRoom = new Room("2301", 30, true, new ArrayList<RoomStatus>(5),
                "2320", new ArrayList<String>(3), new ResourceMetadata());
        this.mvc.perform(post("/v2/room").content(asJSON(testRoom)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Ignore
    @Test
    public void testGetRoomWithValidIdExpecting200() throws Exception {
        Room testRoom = new Room("2301", 30, true,
                new ArrayList<RoomStatus>(5), "1612", new ArrayList<String>(3), new ResourceMetadata());
        this.mvc.perform(post("/v2/room").content(asJSON(testRoom)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        this.mvc.perform(get("/v2/room/{id}", "2301").accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2301));
    }

    @Test
    public void testUpdateRoomWithValidRoomExpecting200() throws Exception{
        Room testRoom = new Room("2301", 30, true,
                new ArrayList<RoomStatus>(5), "1612", new ArrayList<String>(3), new ResourceMetadata());
        this.mvc.perform(put("/v2/room").content(asJSON(testRoom)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Ignore
    @Test
    public void testDeleteRoomByIdWithValidIdExpecting200() throws Exception{
        this.mvc.perform(delete("/v2/room/{id}", "2301").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }
}
