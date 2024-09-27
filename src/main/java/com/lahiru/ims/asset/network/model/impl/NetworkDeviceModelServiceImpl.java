package com.lahiru.ims.asset.network.model.impl;

import com.lahiru.ims.asset.network.model.NetworkDeviceModel;
import com.lahiru.ims.asset.network.model.NetworkDeviceModelMapper;
import com.lahiru.ims.asset.network.model.NetworkDeviceModelRepo;
import com.lahiru.ims.asset.network.model.NetworkDeviceModelService;
import com.lahiru.ims.asset.network.model.dto.DeviceModelRequestDto;
import com.lahiru.ims.asset.network.model.dto.DeviceModelResponseDto;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.DataConflictException;
import com.lahiru.ims.exception.NotFoundException;
import jakarta.persistence.OneToMany;
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
    public PaginationResponse<DeviceModelResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<NetworkDeviceModel> allActive = networkDeviceModelRepo.findAllByIsActive(true, pageable);
        return new PaginationResponse<>(allActive.map(NetworkDeviceModelMapper::toDto).stream().toList(), (int) allActive.getTotalElements());
    }

    @Override
    public List<DeviceModelResponseDto> findAll() throws Exception {
        List<NetworkDeviceModel> allActive = networkDeviceModelRepo.findAllByIsActive(true);
        return allActive
                .stream()
                .map(NetworkDeviceModelMapper::toDto)
                .toList();
    }

    @Override
    public DeviceModelResponseDto createOne(DeviceModelRequestDto deviceModelRequestDto) throws Exception {
        if (isExistsByName(deviceModelRequestDto.getName()))
            throw new DataConflictException(DEVICE_MODEL);

        NetworkDeviceModel deviceModel = NetworkDeviceModelMapper.toModel(deviceModelRequestDto);
        NetworkDeviceModel savedModel = networkDeviceModelRepo.save(deviceModel);
        return NetworkDeviceModelMapper.toDto(savedModel);
    }

    @Override
    public DeviceModelResponseDto updateOne(int id, DeviceModelRequestDto deviceModelRequestDto) throws Exception {
        String name = deviceModelRequestDto.getName();
        NetworkDeviceModel deviceModel = findDeviceModel(id);
        if(isExistsByName(name) && !deviceModel.getName().equals(name))
            throw new DataConflictException(DEVICE_MODEL);

        deviceModel.setName(name);
        NetworkDeviceModel updatedDeviceModel = networkDeviceModelRepo.save(deviceModel);
        return NetworkDeviceModelMapper.toDto(updatedDeviceModel);
    }

    @Override
    public DeviceModelResponseDto deleteOne(int id) throws Exception {
        NetworkDeviceModel deviceModel = findDeviceModel(id);
        deviceModel.setIsActive(false);
        NetworkDeviceModel deletedDeviceModel = networkDeviceModelRepo.save(deviceModel);
        return NetworkDeviceModelMapper.toDto(deletedDeviceModel);
    }

    private boolean isExistsByName(String name) {
        return networkDeviceModelRepo.existsByNameAndIsActive(name, true);
    }

    private NetworkDeviceModel findDeviceModel(Integer id) {
        return networkDeviceModelRepo.findByIdAndIsActive(id, true)
                .orElseThrow(() -> new NotFoundException(DEVICE_MODEL));
    }
}
