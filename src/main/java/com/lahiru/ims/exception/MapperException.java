package com.lahiru.ims.exception;

import java.util.Objects;

public class MapperException extends RuntimeException{
    private MapperException(String message) {
        super(message);
    }
    public MapperException(Exception e) {
        String message = e.getMessage();
        if (Objects.equals(e.getClass(), NotFoundException.class)) throw new NotFoundException(message, true);
        else throw new MapperException(message);
    }
}
