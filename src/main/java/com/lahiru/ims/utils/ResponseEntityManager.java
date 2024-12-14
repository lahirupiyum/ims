package com.lahiru.ims.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;

public class ResponseEntityManager {

    private static final int CREATED_RESPONSE_VALUE = HttpStatus.CREATED.value();
    public static final int NOT_FOUND = HttpStatus.NOT_FOUND.value();

    public static <T> ResponseEntity<PaginationResponse<T>> page(PaginationResponse<T> page) {
        return ResponseEntity.ok().body(page);
    }

    public static <T> ResponseEntity<StandardReponse<T>> created(T data, String resourceName) {
        return ResponseEntity.status(CREATED_RESPONSE_VALUE)
                .body(new StandardReponse<>(CREATED_RESPONSE_VALUE,
                        String.format("%s has been created successfully!", resourceName),
                        data));
    }

    public static <T> ResponseEntity<StandardReponse<T>> ok(T data) {
        return ResponseEntity.ok().body(new StandardReponse<>(data));
    }

    public static ResponseEntity<StandardReponse<Object>> error(int statusCode, String message) {
        return ResponseEntity.status(statusCode).body(new StandardReponse<>(statusCode, message, null));
    }

    public static ResponseEntity<StandardReponse<Object>> notFound(String message) {
        return error(NOT_FOUND, message);
    }

    public static ResponseEntity<StandardReponse<Object>> conflict(String message) {
        return error(HttpStatus.CONFLICT.value(), message);
    }

    public static ResponseEntity<StandardReponse<Object>> badRequest(String message) {
        return error(HttpStatus.BAD_REQUEST.value(), message);
    }

    public static ResponseEntity<StandardReponse<Object>> internalServerError(String message) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }
}
