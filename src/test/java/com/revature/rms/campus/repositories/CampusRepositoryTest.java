package com.revature.rms.campus.repositories;

import com.revature.rms.campus.entities.Address;
import com.revature.rms.campus.entities.Campus;
import com.revature.rms.core.metadata.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CampusRepositoryTest {

    @Autowired
    private CampusRepository sut;

    @AfterEach
    public void tearDown() throws Exception {
        this.sut.deleteAll();
    }

    @Test
    public void testFindByName() {
        Campus campus = new Campus("University of Central Florida", "UCF",
                new Address(1,"","","","",""),1,1,1,
                new ArrayList<>(),new ArrayList<>(),
                new ResourceMetadata());
        sut.save(campus);
        Campus campus1 = sut.findByName("University of Central Florida");
        assertThat(campus1.getAbbrName()).isEqualTo(campus.getAbbrName());
    }

    @Test
    public void testFindByStagingManagerId() {
        Campus campus = new Campus("University", "USFT",
                new Address(1,"","","","",""),1,7,1,
                new ArrayList<>(),new ArrayList<>(),
                new ResourceMetadata());

        sut.save(campus);
        List<Campus> campusList = sut.findByStagingManagerId(7);

        assertThat(campusList.size()).isEqualTo(1);

    }

    @Test
    public void testFindByTrainingManagerId() {
        Campus campus = new Campus("University of Test", "USFTEST",
                new Address(1,"","","","",""),4,7,1,
                new ArrayList<>(),new ArrayList<>(),
                new ResourceMetadata());

        sut.save(campus);
        List<Campus> campusList = sut.findByTrainingManagerId(4);

        assertThat(campusList.size()).isEqualTo(1);

    }
}