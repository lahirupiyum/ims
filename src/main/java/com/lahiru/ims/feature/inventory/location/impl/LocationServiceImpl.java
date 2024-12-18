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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Location> locationPage = repository.findAll(pageable);
        List<LocationResponseDto> locationList = modelMapper
                .map(locationPage.stream().toList()
                        , new TypeToken<List<LocationResponseDto>>() {
                        }.getType());
        return new PaginationResponse<>(
                locationList,
                (int) locationPage.getTotalElements()
        );
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
        Location location = modelMapper.map(locationRequestDto, Location.class);
        Location saveLocation = repository.save(location);
        return modelMapper.map(saveLocation, LocationResponseDto.class);
    }

    @Override
    public LocationResponseDto updateOne(int id, LocationRequestDto locationRequestDto) throws Exception {
        Location findLocation = repository.findById(id).orElseThrow(() -> new NotFoundException(LOCATION));
        findLocation.setAddress(locationRequestDto.getAddress());
        findLocation.setName(locationRequestDto.getName());
        Location updatedLocation = repository.saveAndFlush(findLocation);
        return modelMapper.map(updatedLocation, LocationResponseDto.class);
    }

    @Override
    public LocationResponseDto deleteOne(int id) throws Exception {
        Location location = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(LOCATION));
        if (location != null) {
            repository.deleteById(id);
        }
        return modelMapper.map(location, LocationResponseDto.class);
    }
//
//    public static final String BRANCH = "Branch";
//    private final LocationRepo locationRepo;
//
//    @Override
//    public PaginationResponse<LocationResponseDto> findByPageWise(int page, int pageSize) throws Exception {
//        Pageable pageable = PageRequest.of(page, pageSize);
//        Page<Location> allActive = locationRepo.findAllActive(pageable);
//
//        List<LocationResponseDto> branchList = allActive.map(LocationMapper::toDto).toList();
//        int totalElements = (int) allActive.getTotalElements();
//
//        return new PaginationResponse<>(branchList, totalElements);
//    }
//
//    @Override
//    public List<LocationResponseDto> findAll() throws Exception {
//        List<Location> allActive = locationRepo.findAllActive();
//        return allActive.stream().map(LocationMapper::toDto).toList();
//    }
//
//    @Override
//    public LocationResponseDto createOne(LocationRequestDto requestDto) throws Exception {
//        Location location = LocationMapper.toModel(requestDto);
//        Location savedLocation = locationRepo.save(location);
//        return LocationMapper.toDto(savedLocation);
//    }
//
//    @Override
//    public LocationResponseDto updateOne(int id, LocationRequestDto requestDto) throws Exception {
//        if(!locationRepo.existsByIdAndStatus(id, true))
//            throw new NotFoundException(BRANCH);
//        Location location = LocationMapper.toModel(requestDto);
//        location.setId(id);
//        Location savedLocation = locationRepo.save(location);
//        return LocationMapper.toDto(savedLocation);
//    }
//
//    @Override
//    public LocationResponseDto deleteOne(int id) throws Exception {
//        Location location = locationRepo.findActiveById(id)
//                            .orElseThrow(() -> new NotFoundException(BRANCH));
//
//        locationRepo.save(location);
//        return LocationMapper.toDto(location);
//    }
//
}
