package integration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.rms.campus.CampusServiceApplication;
import com.revature.rms.campus.entities.Building;
import com.revature.rms.campus.services.BuildingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CampusServiceApplication.class)
@AutoConfigureMockMvc
public class BuildingControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    BuildingService buildingService;

    Building building;
    ObjectMapper mapper;

    @Before
    public void setUp(){
        building = new Building(1, null, "test", null, 1);
        mapper = new ObjectMapper();
    }

    /**
     * This ensures save building hits the desired endpoint provided, produces a JSON object retrieved from
     * the database, and returns a status of 200 if everything was successful.
     * @throws Exception from perform()
     */
    @Test
    public void testSaveBuilding() throws Exception{
        Mockito.when(buildingService.save(Mockito.any(Building.class))).thenReturn(building);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/campuses/buildings")
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(building))
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.abbrName", is("test")));
    }

    /**
     * This ensures get all buildings hits the desired endpoint provided, produces a JSON object retrieved from
     * the database, and returns a status of 200 if everything was successful.
     * @throws Exception from perform()
     */
    @Test
    public void testGetAllBuildings() throws Exception{
        List<Building> buildings = Arrays.asList(building);
        Mockito.when(buildingService.findAll()).thenReturn(buildings);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/campuses/buildings")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].abbrName", is("test")));
    }

    /**
     * This ensures get building by id hits the desired endpoint provided, produces a JSON object retrieved from
     * the database, and returns a status of 200 if everything was successful.
     * @throws Exception from perform()
     */
    @Test
    public void testGetBuildingById() throws Exception{
        Mockito.when(buildingService.findById(1)).thenReturn(Optional.of(building));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/campuses/buildings/id/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.abbrName", is("test")));
    }

    /**
     * This ensures get building by trainer id hits the desired endpoint provided, produces a JSON object retrieved from
     * the database, and returns a status of 200 if everything was successful.
     * @throws Exception from perform()
     */
    @Test
    public void testGetBuildingByTrainerId() throws Exception{
        Mockito.when(buildingService.findByTrainingLeadId(1)).thenReturn(building);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/campuses/buildings/trainers/id/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.abbrName", is("test")));
    }
}
