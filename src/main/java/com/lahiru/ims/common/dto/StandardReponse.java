package com.lahiru.ims.common.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class StandardReponse<T> {
    private int statusCode;
    private String message;
    private T data;
    private LocalDateTime timeStamp;

    public StandardReponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.timeStamp = LocalDateTime.now();
    }

    public StandardReponse(String message, T data) {
        this(HttpStatus.OK.value(), message, data);
    }

    public StandardReponse(T data) {
        this("", data);
    }

}
