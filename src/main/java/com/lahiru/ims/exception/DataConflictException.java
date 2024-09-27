package com.lahiru.ims.exception;

public class DataConflictException extends RuntimeException {
    public DataConflictException(String resourceName) {
        super(String.format("%s is already exists!", resourceName));
    }
}
