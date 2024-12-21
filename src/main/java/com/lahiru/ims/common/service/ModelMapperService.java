package com.lahiru.ims.common.service;

public interface ModelMapperService <Model, RequestDto, ResponseDto> {
    Model convertToModel(RequestDto requestDto) throws Exception;
    ResponseDto convertToDto(Model model) throws Exception;
}
