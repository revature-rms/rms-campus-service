package integration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.rms.campus.RoomServiceApplication;
import com.revature.rms.campus.entities.ResourceMetadata;
import com.revature.rms.campus.entities.Room;
import com.revature.rms.campus.entities.RoomStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = RoomServiceApplication.class)
@AutoConfigureMockMvc
public class RoomControllersIntegrationTests {

    @Autowired
    private MockMvc mvc;

    public static String asJSON(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetAllRoomWithExistingRoomException200() throws Exception {
        Room testRoom = new Room("1", "2301", 25, true, new ArrayList<RoomStatus>(5),
                "1612", new ArrayList<String>(3), new ResourceMetadata());

        this.mvc.perform(get("/v2/room").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testSaveRoomWithValidRoomExpecting200() throws Exception{
        Room testRoom = new Room("2301", 25, true, new ArrayList<RoomStatus>(5),
                "1612", new ArrayList<String>(3), new ResourceMetadata());

        this.mvc.perform(post("/v2/room").content(asJSON(testRoom)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
