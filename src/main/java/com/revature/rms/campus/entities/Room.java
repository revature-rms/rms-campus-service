package com.revature.rms.campus.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
//import org.springframework.data.mongodb.core.mapping.Document;
import com.revature.rms.core.metadata.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


//@Document

//commentted out bc mangodb
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Data
public class Room {
    /**
     * May need to add @NotNull annotations to some fields to prevent null values.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false)
    private String roomNumber;
    
    @Column(nullable=false)
    private int maxOccupancy;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "room")
    private List<RoomStatus> currentStatus;

    @Column(nullable=false)
    private int batchId;

    @ElementCollection
    private List<Integer> workOrders;

    @Embedded
    private ResourceMetadata resourceMetadata;

    @ManyToOne
    @JsonIgnore
    private Building building;

    public Room() {
    }

    public Room(int id, String roomNumber, Integer maxOccupancy, ArrayList<RoomStatus> currentStatus, int batchId, ArrayList<Integer> workOrders, ResourceMetadata resourceMetadata) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.maxOccupancy = maxOccupancy;
        this.currentStatus = currentStatus;
        this.batchId = batchId;
        this.workOrders = workOrders;
        this.resourceMetadata = resourceMetadata;
    }

    public Room(String roomNumber, Integer maxOccupancy, ArrayList<RoomStatus> currentStatus, int batchId, ArrayList<Integer> workOrders, ResourceMetadata resourceMetadata) {
        this.roomNumber = roomNumber;
        this.maxOccupancy = maxOccupancy;
        this.currentStatus = currentStatus;
        this.batchId = batchId;
        this.workOrders = workOrders;
        this.resourceMetadata = resourceMetadata;
    }

    public void addRoomStatus(RoomStatus status) {
        currentStatus.add(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public List<RoomStatus> getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(List<RoomStatus> currentStatus) {
        this.currentStatus = currentStatus;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public List<Integer> getWorkOrders() {
        return workOrders;
    }

    public void setWorkOrders(List<Integer> workOrders) {
        this.workOrders = workOrders;
    }

    public ResourceMetadata getResourceMetadata() {
        return resourceMetadata;
    }

    public void setResourceMetadata(ResourceMetadata resourceMetadata) {
        this.resourceMetadata = resourceMetadata;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
