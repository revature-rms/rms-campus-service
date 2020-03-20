package com.revature.rms.campus.entities;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResourceMetadata {

    private int resourceCreator;
    private String resourceCreationDateTime;
    private int lastModifier;
    private String lastModifiedDateTime;
    private int resourceOwner;

    public ResourceMetadata(String resourceCreationDateTime, int lastModifier, String lastModifiedDateTime, int resourceOwner) {
        this.resourceCreationDateTime = resourceCreationDateTime;
        this.lastModifier = lastModifier;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.resourceOwner = resourceOwner;
    }


}
