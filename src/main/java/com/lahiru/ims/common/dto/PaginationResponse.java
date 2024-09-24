package com.lahiru.ims.common.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class PaginationResponse<T> {
    private int statusCode;
    private List<T> data;
    private Integer totalCount;
    private LocalDateTime timeStamp;

    public PaginationResponse(List<T> data, Integer totalCount) {
        this.statusCode = HttpStatus.OK.value();
        this.data = data;
        this.totalCount = totalCount;
        this.timeStamp = LocalDateTime.now();
    }
}
