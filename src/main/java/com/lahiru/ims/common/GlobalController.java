package com.lahiru.ims.common;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

public interface GlobalController<RequestDto, ResponseDto> {
    @GetMapping
    ResponseEntity<StandardReponse<PaginationResponse<ResponseDto>>> getAllByPageWise(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize)
            throws Exception;

    @GetMapping("/all")
    ResponseEntity<StandardReponse<List<ResponseDto>>> getAll() throws Exception;

    @PostMapping
    ResponseEntity<StandardReponse<ResponseDto>> createOne(@Valid @RequestBody RequestDto requestDto) throws Exception;

    @PutMapping("/{id}")
    ResponseEntity<StandardReponse<ResponseDto>> updateOne(
            @PathVariable int id,
            @Valid @RequestBody RequestDto requestDto) throws Exception;

    @DeleteMapping("/{id}")
    ResponseEntity<StandardReponse<ResponseDto>> deleteOne(@PathVariable int id) throws Exception;
}
