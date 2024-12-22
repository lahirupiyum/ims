package com.lahiru.ims.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String resource) {
        super(String.format("%s not found!", resource));
    }
    public NotFoundException(String message, boolean isFullMessage) {
        super((isFullMessage ? message : String.format("%s not found!", message)));
    }
}
