package com.lahiru.ims.feature.inventory.location.impl;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.inventory.location.Location;
import com.lahiru.ims.feature.inventory.location.LocationRepo;
import com.lahiru.ims.feature.inventory.location.LocationService;
import com.lahiru.ims.feature.inventory.location.dto.LocationRequestDto;
import com.lahiru.ims.feature.inventory.location.dto.LocationResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
//@Transactional
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final ModelMapper modelMapper;
    private final LocationRepo repository;
    public static final String LOCATION = "Location";

    @Override
    public PaginationResponse<LocationResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        return null;
    }

    @Override
    public List<LocationResponseDto> findAll() throws Exception {
        List<Location> locationList = repository.findAll();
        List<LocationResponseDto> responseLocation = modelMapper.map(locationList, new TypeToken<List<LocationResponseDto>>() {
        }.getType());
        return (!responseLocation.isEmpty()) ? responseLocation : Collections.emptyList();
    }

    @Override
    public LocationResponseDto createOne(LocationRequestDto locationRequestDto) throws Exception {
        return null;
    }

    @Override
    public LocationResponseDto updateOne(int id, LocationRequestDto locationRequestDto) throws Exception {
        return null;
    }

    @Override
    public LocationResponseDto deleteOne(int id) throws Exception {
        return null;
    }

    @Override
    public Location findOne(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(LOCATION));
    }
}
