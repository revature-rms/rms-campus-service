package com.revature.rms.campus.exceptions;

public class ResourcePersistenceException extends RuntimeException {
    public ResourcePersistenceException () {
        super("This resource was not persisted correctly.");
    }

    public ResourcePersistenceException(String message) {
        super(message);
    }
}
