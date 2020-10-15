package com.revature.rms.campus.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
//import org.springframework.data.mongodb.core.mapping.Document;
import com.revature.rms.core.metadata.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * POJO for the campus object. The annotations:
 * @Data handles the getter and setter methods for each field
 * @Id marks the selected field as a primary key
 *
 * Lastly, we have a constructor containing every field except for the id.
 */
@Data
@Entity
public class Campus {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false,unique=true)
    private String name;

    @Column(nullable=false)
    private String abbrName;

    @OneToOne
    @JoinColumn(nullable=false)
    private Address shippingAddress;

    @Column(nullable=false)
    private int trainingManagerId;

    @Column(nullable=false)
    private int stagingManagerId;

    @Column(nullable=false)
    private int hrLead;

    @OneToMany(mappedBy = "campus")
    private List<Building> buildings;

    @ElementCollection
    private List<Integer> corporateEmployees;

    @Embedded
    private ResourceMetadata resourceMetadata;

    public Campus() {
    }

    public Campus(int id, String name, String abbrName, Address shippingAddress, int trainingManagerId, int stagingManagerId, int hrLead, ArrayList<Building> buildings, ArrayList<Integer> corporateEmployees, ResourceMetadata resourceMetadata) {
        this.id = id;
        this.name = name;
        this.abbrName = abbrName;
        this.shippingAddress = shippingAddress;
        this.trainingManagerId = trainingManagerId;
        this.stagingManagerId = stagingManagerId;
        this.hrLead = hrLead;
        this.buildings = buildings;
        this.corporateEmployees = corporateEmployees;
        this.resourceMetadata = resourceMetadata;
    }

    public Campus(String name, String abbrName, Address shippingAddress, int trainingManagerId, int stagingManagerId, int hrLead, ArrayList<Building> buildings, ArrayList<Integer> corporateEmployees, ResourceMetadata resourceMetadata) {
        this.name = name;
        this.abbrName = abbrName;
        this.shippingAddress = shippingAddress;
        this.trainingManagerId = trainingManagerId;
        this.stagingManagerId = stagingManagerId;
        this.hrLead = hrLead;
        this.buildings = buildings;
        this.corporateEmployees = corporateEmployees;
        this.resourceMetadata = resourceMetadata;
    }

    public Campus(int id, String name, String abbrName, Address shippingAddress, int trainingManagerId, int stagingManagerId, int hrLead, List<Integer> corporateEmployees, ResourceMetadata resourceMetadata) {
        this.id = id;
        this.name = name;
        this.abbrName = abbrName;
        this.shippingAddress = shippingAddress;
        this.trainingManagerId = trainingManagerId;
        this.stagingManagerId = stagingManagerId;
        this.hrLead = hrLead;
        this.corporateEmployees = corporateEmployees;
        this.resourceMetadata = resourceMetadata;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbrName() {
        return abbrName;
    }

    public void setAbbrName(String abbrName) {
        this.abbrName = abbrName;
    }

    public ResourceMetadata getResourceMetadata() {
        return resourceMetadata;
    }

    public void setResourceMetadata(ResourceMetadata resourceMetadata) {
        this.resourceMetadata = resourceMetadata;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
