package com.lahiru.ims.feature.inventory.location;

import com.lahiru.ims.common.controller.GenericController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;

import com.lahiru.ims.feature.inventory.location.dto.LocationRequestDto;
import com.lahiru.ims.feature.inventory.location.dto.LocationResponseDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${endpoints.location}")
@RequiredArgsConstructor
public class LocationController implements GenericController<LocationRequestDto, LocationResponseDto> {

    private final LocationService locationService;
    private final Logger logger = LoggerFactory.getLogger(LocationController.class);
    private final int httpOkStatusCode = HttpStatus.OK.value();


    @Override
    public ResponseEntity<PaginationResponse<LocationResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<StandardReponse<List<LocationResponseDto>>> getAll() throws Exception {
        List<LocationResponseDto> locations = locationService.findAll();
        return ResponseEntityManager.ok(locations);
    }

    @Override
    public ResponseEntity<StandardReponse<LocationResponseDto>> createOne(LocationRequestDto locationRequestDto) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<StandardReponse<LocationResponseDto>> updateOne(int id, LocationRequestDto locationRequestDto) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<StandardReponse<LocationResponseDto>> deleteOne(int id) throws Exception {
        return null;
    }
}
