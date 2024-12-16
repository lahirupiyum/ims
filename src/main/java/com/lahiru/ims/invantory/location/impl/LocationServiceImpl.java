package com.lahiru.ims.invantory.location.impl;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.invantory.location.Location;
import com.lahiru.ims.invantory.location.LocationMapper;
import com.lahiru.ims.invantory.location.LocationRepo;
import com.lahiru.ims.invantory.location.LocationService;
import com.lahiru.ims.invantory.location.dto.LocationRequestDto;
import com.lahiru.ims.invantory.location.dto.LocationResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    public static final String BRANCH = "Branch";
    private final LocationRepo locationRepo;

    @Override
    public PaginationResponse<LocationResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Location> allActive = locationRepo.findAllActive(pageable);

        List<LocationResponseDto> branchList = allActive.map(LocationMapper::toDto).toList();
        int totalElements = (int) allActive.getTotalElements();
    
        return new PaginationResponse<>(branchList, totalElements);
    }

    @Override
    public List<LocationResponseDto> findAll() throws Exception {
        List<Location> allActive = locationRepo.findAllActive();
        return allActive.stream().map(LocationMapper::toDto).toList();
    }

    @Override
    public LocationResponseDto createOne(LocationRequestDto requestDto) throws Exception {
        Location location = LocationMapper.toModel(requestDto);
        Location savedLocation = locationRepo.save(location);
        return LocationMapper.toDto(savedLocation);
    }

    @Override
    public LocationResponseDto updateOne(int id, LocationRequestDto requestDto) throws Exception {
        if(!locationRepo.existsByIdAndStatus(id, true))
            throw new NotFoundException(BRANCH);
        Location location = LocationMapper.toModel(requestDto);
        location.setId(id);
        Location savedLocation = locationRepo.save(location);
        return LocationMapper.toDto(savedLocation);
    }

    @Override
    public LocationResponseDto deleteOne(int id) throws Exception {
        Location location = locationRepo.findActiveById(id)
                            .orElseThrow(() -> new NotFoundException(BRANCH));
        location.setStatus(false);
        locationRepo.save(location);
        return LocationMapper.toDto(location);
    }
    
}
