package com.lahiru.ims.exception;

public class DataConflictException extends RuntimeException {
    public DataConflictException(String resourceName) {
        super(String.format("%s is already exists!", resourceName));
    }
    public DataConflictException(String message, Boolean isFullMessage) {
        super(isFullMessage ? message : String.format("%s is already exists!", message));
    }
}
