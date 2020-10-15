package com.revature.rms.campus.exceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super("This entry is not valid");
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
