package com.lahiru.ims.asset.network.manufacturer.impl;

import com.lahiru.ims.asset.network.manufacturer.ManufacturerMapper;
import com.lahiru.ims.asset.network.manufacturer.NetworkDeviceManufacturer;
import com.lahiru.ims.asset.network.manufacturer.NetworkDeviceManufacturerRepo;
import com.lahiru.ims.asset.network.manufacturer.NetworkDeviceManufacturerService;
import com.lahiru.ims.asset.network.manufacturer.dto.ManufacturerRequestDto;
import com.lahiru.ims.asset.network.manufacturer.dto.ManufacturerResponseDto;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.DataConflictException;
import com.lahiru.ims.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements NetworkDeviceManufacturerService {
    public static final String NETWORK_MANUFACTURER = "Network Manufacturer";
    private final NetworkDeviceManufacturerRepo manufacturerRepo;

    @Override
    public PaginationResponse<ManufacturerResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<NetworkDeviceManufacturer> allActive = manufacturerRepo.findAllActive(pageable);
        return new PaginationResponse<>(allActive.map(ManufacturerMapper::toDto).stream().toList(), (int) allActive.getTotalElements());
    }

    @Override
    public List<ManufacturerResponseDto> findAll() throws Exception {
        List<NetworkDeviceManufacturer> allActive = manufacturerRepo.findAllActive();
        return allActive.stream().map(ManufacturerMapper::toDto).toList();
    }

    @Override
    public ManufacturerResponseDto createOne(ManufacturerRequestDto manufacturerRequestDto) throws Exception {
        if(manufacturerRepo.existsByNameAndIsActive(manufacturerRequestDto.getName(), true))
            throw new DataConflictException("Manufacturer already exists");

        NetworkDeviceManufacturer manufacturer = ManufacturerMapper.toModel(manufacturerRequestDto);
        NetworkDeviceManufacturer savedManufacturer = manufacturerRepo.save(manufacturer);
        return ManufacturerMapper.toDto(savedManufacturer);
    }

    @Override
    public ManufacturerResponseDto updateOne(int id, ManufacturerRequestDto manufacturerRequestDto) throws Exception {
        String updatedName = manufacturerRequestDto.getName();
        NetworkDeviceManufacturer manufacturer = manufacturerRepo.findActiveById(id)
                .orElseThrow(() -> new NotFoundException(NETWORK_MANUFACTURER));

        boolean isManufacturerEqualsCurrent = manufacturer.getName().equals(updatedName);
        boolean isManufacturerExists = manufacturerRepo.existsByNameAndIsActive(updatedName, true) && !isManufacturerEqualsCurrent;

        if (isManufacturerExists)
            throw new DataConflictException("Manufacturer already exists!");

        manufacturer.setName(updatedName);

        NetworkDeviceManufacturer updatedManufacturer = manufacturerRepo.save(manufacturer);

        return ManufacturerMapper.toDto(updatedManufacturer);
    }

    @Override
    public ManufacturerResponseDto deleteOne(int id) throws Exception {
        NetworkDeviceManufacturer manufacturer = manufacturerRepo.findActiveById(id)
                .orElseThrow(() -> new NotFoundException(NETWORK_MANUFACTURER));
        manufacturer.setIsActive(false);
        NetworkDeviceManufacturer deletedManufacturer = manufacturerRepo.save(manufacturer);
        return ManufacturerMapper.toDto(deletedManufacturer);
    }
}
