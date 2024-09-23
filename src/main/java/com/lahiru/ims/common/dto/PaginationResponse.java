package com.lahiru.ims.common.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginationResponse<T> {
    private List<T> data;
    private Integer totalCount;
}
