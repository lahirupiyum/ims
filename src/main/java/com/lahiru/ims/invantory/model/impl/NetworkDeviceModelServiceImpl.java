package com.lahiru.ims.invantory.model.impl;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.DataConflictException;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.invantory.model.Model;
import com.lahiru.ims.invantory.model.NetworkDeviceModelMapper;
import com.lahiru.ims.invantory.model.NetworkDeviceModelRepo;
import com.lahiru.ims.invantory.model.NetworkDeviceModelService;
import com.lahiru.ims.invantory.model.dto.NetworkDeviceModelRequestDto;
import com.lahiru.ims.invantory.model.dto.NetworkDeviceModelResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NetworkDeviceModelServiceImpl implements NetworkDeviceModelService {

    public static final String DEVICE_MODEL = "Device model";
    private final NetworkDeviceModelRepo networkDeviceModelRepo;

    @Override
    public PaginationResponse<NetworkDeviceModelResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Model> allActive = networkDeviceModelRepo.findAllByIsActive(true, pageable);
        return new PaginationResponse<>(allActive.map(NetworkDeviceModelMapper::toDto).stream().toList(), (int) allActive.getTotalElements());
    }

    @Override
    public List<NetworkDeviceModelResponseDto> findAll() throws Exception {
        List<Model> allActive = networkDeviceModelRepo.findAllByIsActive(true);
        return allActive
                .stream()
                .map(NetworkDeviceModelMapper::toDto)
                .toList();
    }

    @Override
    public NetworkDeviceModelResponseDto createOne(NetworkDeviceModelRequestDto networkDeviceModelRequestDto) throws Exception {
        if (isExistsByName(networkDeviceModelRequestDto.getName()))
            throw new DataConflictException(DEVICE_MODEL);

        Model deviceModel = NetworkDeviceModelMapper.toModel(networkDeviceModelRequestDto);
        Model savedModel = networkDeviceModelRepo.save(deviceModel);
        return NetworkDeviceModelMapper.toDto(savedModel);
    }

    @Override
    public NetworkDeviceModelResponseDto updateOne(int id, NetworkDeviceModelRequestDto networkDeviceModelRequestDto) throws Exception {
        String name = networkDeviceModelRequestDto.getName();
        Model deviceModel = findDeviceModel(id);
        if(isExistsByName(name) && !deviceModel.getName().equals(name))
            throw new DataConflictException(DEVICE_MODEL);

        deviceModel.setName(name);
        Model updatedDeviceModel = networkDeviceModelRepo.save(deviceModel);
        return NetworkDeviceModelMapper.toDto(updatedDeviceModel);
    }

    @Override
    public NetworkDeviceModelResponseDto deleteOne(int id) throws Exception {
        Model deviceModel = findDeviceModel(id);
        deviceModel.setActive(  false);
        Model deletedDeviceModel = networkDeviceModelRepo.save(deviceModel);
        return NetworkDeviceModelMapper.toDto(deletedDeviceModel);
    }

    private boolean isExistsByName(String name) {
        return networkDeviceModelRepo.existsByNameAndIsActive(name, true);
    }

    private Model findDeviceModel(Integer id) {
        return networkDeviceModelRepo.findByIdAndIsActive(id, true)
                .orElseThrow(() -> new NotFoundException(DEVICE_MODEL));
    }
}
