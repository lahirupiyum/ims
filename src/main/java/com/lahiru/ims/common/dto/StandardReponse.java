package com.lahiru.ims.common.dto;

import java.time.LocalDateTime;

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
}
