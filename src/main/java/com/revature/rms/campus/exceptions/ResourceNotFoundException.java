package com.revature.rms.campus.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("The specified resource is not available at this time");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
