package com.lahiru.ims.common;

import java.util.List;

import com.lahiru.ims.common.dto.PaginationResponse;

public interface GenericService<RequestDto, ResponseDto> {
    PaginationResponse<ResponseDto> findByPageWise(int page, int pageSize) throws Exception;
    List<ResponseDto> findAll() throws Exception;
    ResponseDto createOne(RequestDto requestDto) throws Exception;
    ResponseDto updateOne(int id, RequestDto requestDto) throws Exception;
    ResponseDto deleteOne(int id) throws Exception;
}
