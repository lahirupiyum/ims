package com.lahiru.ims.features.inventory.asset.network.manufacturer.impl;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.DataConflictException;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.features.inventory.asset.network.manufacturer.NetworkDeviceManufacturer;
import com.lahiru.ims.features.inventory.asset.network.manufacturer.NetworkDeviceManufacturerMapper;
import com.lahiru.ims.features.inventory.asset.network.manufacturer.NetworkDeviceManufacturerRepo;
import com.lahiru.ims.features.inventory.asset.network.manufacturer.NetworkDeviceManufacturerService;
import com.lahiru.ims.features.inventory.asset.network.manufacturer.dto.NetworkDeviceManufacturerRequestDto;
import com.lahiru.ims.features.inventory.asset.network.manufacturer.dto.NetworkDeviceManufacturerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements NetworkDeviceManufacturerService {
    public static final String DEVICE_MANUFACTURER = "Network device manufacturer";
    private final NetworkDeviceManufacturerRepo manufacturerRepo;

    @Override
    public PaginationResponse<NetworkDeviceManufacturerResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<NetworkDeviceManufacturer> allActive = manufacturerRepo.findAllActive(pageable);
        return new PaginationResponse<>(allActive.map(NetworkDeviceManufacturerMapper::toDto).stream().toList(), (int) allActive.getTotalElements());
    }

    @Override
    public List<NetworkDeviceManufacturerResponseDto> findAll() throws Exception {
        List<NetworkDeviceManufacturer> allActive = manufacturerRepo.findAllActive();
        return allActive.stream().map(NetworkDeviceManufacturerMapper::toDto).toList();
    }

    @Override
    public NetworkDeviceManufacturerResponseDto createOne(NetworkDeviceManufacturerRequestDto networkDeviceManufacturerRequestDto) throws Exception {
        if(manufacturerRepo.existsByNameAndIsActive(networkDeviceManufacturerRequestDto.getName(), true))
            throw new DataConflictException(DEVICE_MANUFACTURER);

        NetworkDeviceManufacturer manufacturer = NetworkDeviceManufacturerMapper.toModel(networkDeviceManufacturerRequestDto);
        NetworkDeviceManufacturer savedManufacturer = manufacturerRepo.save(manufacturer);
        return NetworkDeviceManufacturerMapper.toDto(savedManufacturer);
    }

    @Override
    public NetworkDeviceManufacturerResponseDto updateOne(int id, NetworkDeviceManufacturerRequestDto networkDeviceManufacturerRequestDto) throws Exception {
        String updatedName = networkDeviceManufacturerRequestDto.getName();
        NetworkDeviceManufacturer manufacturer = manufacturerRepo.findActiveById(id)
                .orElseThrow(() -> new NotFoundException(DEVICE_MANUFACTURER));

        boolean isManufacturerEqualsCurrent = manufacturer.getName().equals(updatedName);
        boolean isManufacturerExists = manufacturerRepo.existsByNameAndIsActive(updatedName, true) && !isManufacturerEqualsCurrent;

        if (isManufacturerExists)
            throw new DataConflictException(DEVICE_MANUFACTURER);

        manufacturer.setName(updatedName);

        NetworkDeviceManufacturer updatedManufacturer = manufacturerRepo.save(manufacturer);

        return NetworkDeviceManufacturerMapper.toDto(updatedManufacturer);
    }

    @Override
    public NetworkDeviceManufacturerResponseDto deleteOne(int id) throws Exception {
        NetworkDeviceManufacturer manufacturer = manufacturerRepo.findActiveById(id)
                .orElseThrow(() -> new NotFoundException(DEVICE_MANUFACTURER));
        manufacturer.setIsActive(false);
        NetworkDeviceManufacturer deletedManufacturer = manufacturerRepo.save(manufacturer);
        return NetworkDeviceManufacturerMapper.toDto(deletedManufacturer);
    }
}
