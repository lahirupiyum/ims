package com.lahiru.ims.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;

public class ResponseEntityManager {

    private static final int CREATED_RESPONSE_VALUE = HttpStatus.CREATED.value();

    public static <T> ResponseEntity<PaginationResponse<T>> getPaginationResponse(PaginationResponse<T> page) {
        return ResponseEntity.ok().body(page);
    }

    public static <T> ResponseEntity<StandardReponse<T>> getCreateResponse(T data, String resourceName) {
        return ResponseEntity.status(CREATED_RESPONSE_VALUE)
                .body(new StandardReponse<>(CREATED_RESPONSE_VALUE,
                        String.format("%s has been created successfully!", resourceName),
                        data));
    }

    public static <T> ResponseEntity<StandardReponse<T>> getOkResponse(T data) {
        return ResponseEntity.ok().body(new StandardReponse<>(data));
    }
}
