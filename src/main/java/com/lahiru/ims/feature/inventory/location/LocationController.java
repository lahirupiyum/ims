package com.lahiru.ims.feature.inventory.location;

import com.lahiru.ims.common.controller.GenericController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;

import com.lahiru.ims.feature.inventory.location.dto.LocationRequestDto;
import com.lahiru.ims.feature.inventory.location.dto.LocationResponseDto;
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
@RequestMapping("${endpoints.branch}")
@RequiredArgsConstructor
public class LocationController implements GenericController<LocationRequestDto, LocationResponseDto> {

    private final LocationService locationService;
    private final Logger logger = LoggerFactory.getLogger(LocationController.class);
    private final int httpOkStatusCode = HttpStatus.OK.value();

    @Override
    public ResponseEntity<PaginationResponse<LocationResponseDto>> getAllByPageWise(int page,
                                                                                    int pageSize) throws Exception {
        try {
            return ResponseEntity.ok().body(locationService.findByPageWise(page, pageSize));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<StandardReponse<List<LocationResponseDto>>> getAll() throws Exception {
        try {
            return ResponseEntity.ok().body(new StandardReponse<>(httpOkStatusCode, "", locationService.findAll()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<StandardReponse<LocationResponseDto>> createOne(@Valid LocationRequestDto requestDto)
            throws Exception {
        try {
            LocationResponseDto responseDto = locationService.createOne(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED.value()).body(new StandardReponse<>(HttpStatus.CREATED.value(), "",responseDto));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<StandardReponse<LocationResponseDto>> updateOne(int id, @Valid LocationRequestDto requestDto)
            throws Exception {
                try {
                    LocationResponseDto responseDto = locationService.updateOne(id, requestDto);
                    return ResponseEntity.ok().body(new StandardReponse<>(responseDto));
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    throw e;
                }
    }

    @Override
    public ResponseEntity<StandardReponse<LocationResponseDto>> deleteOne(int id) throws Exception {
        try {
            LocationResponseDto responseDto = locationService.deleteOne(id);
            return ResponseEntity.ok().body(new StandardReponse<>(responseDto));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
    
}
