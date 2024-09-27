package com.lahiru.ims.asset.network.device.impl;

import com.lahiru.ims.asset.network.device.NetworkDevice;
import com.lahiru.ims.asset.network.device.NetworkDeviceMapper;
import com.lahiru.ims.asset.network.device.NetworkDeviceRepo;
import com.lahiru.ims.asset.network.device.NetworkDeviceService;
import com.lahiru.ims.asset.network.device.dto.NetworkDeviceRequestDto;
import com.lahiru.ims.asset.network.device.dto.NetworkDeviceResponseDto;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.DataConflictException;
import com.lahiru.ims.exception.NotFoundException;
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
public class NetworkDeviceServiceImpl implements NetworkDeviceService {

    public static final String DEVICE_SERIAL_NUMBER = "Device serial number";
    public static final String NETWORK_DEVICE = "Network device";
    private final NetworkDeviceRepo networkDeviceRepo;
    private final NetworkDeviceMapper networkDeviceMapper;

    @Override
    public PaginationResponse<NetworkDeviceResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<NetworkDevice> allActive = networkDeviceRepo.findAllByIsActiveIsTrue(pageable);
        return new PaginationResponse<>(allActive.map(networkDeviceMapper::toDto).stream().toList(), (int) allActive.getTotalElements());
    }

    @Override
    public List<NetworkDeviceResponseDto> findAll() throws Exception {
        List<NetworkDevice> allActive = networkDeviceRepo.findAllByIsActiveIsTrue();
        return allActive.stream().map(networkDeviceMapper::toDto).toList();
    }

    @Override
    public NetworkDeviceResponseDto createOne(NetworkDeviceRequestDto networkDeviceRequestDto) throws Exception {
        if (isDeviceExistsBySerialNo(networkDeviceRequestDto.getSerialNumber()))
            throw new DataConflictException(DEVICE_SERIAL_NUMBER);
        NetworkDevice networkDevice = networkDeviceMapper.toModel(networkDeviceRequestDto);
        NetworkDevice savedNetworkDevice = networkDeviceRepo.save(networkDevice);
        return networkDeviceMapper.toDto(savedNetworkDevice);
    }

    @Override
    public NetworkDeviceResponseDto updateOne(int id, NetworkDeviceRequestDto networkDeviceRequestDto) throws Exception {
        String serialNumber = networkDeviceRequestDto.getSerialNumber();
        if (isDeviceExistsBySerialNo(serialNumber) && !findNetworkDevice(id).getSerialNumber().equals(serialNumber))
            throw new DataConflictException(DEVICE_SERIAL_NUMBER);

        NetworkDevice networkDevice = networkDeviceMapper.toModel(networkDeviceRequestDto);
        networkDevice.setId(id);

        NetworkDevice updatedNetworkDevice = networkDeviceRepo.save(networkDevice);

        return networkDeviceMapper.toDto(updatedNetworkDevice);
    }

    @Override
    public NetworkDeviceResponseDto deleteOne(int id) throws Exception {
        NetworkDevice networkDevice = findNetworkDevice(id);
        networkDevice.setIsActive(false);
        NetworkDevice deletedNetworkDevice = networkDeviceRepo.save(networkDevice);
        return networkDeviceMapper.toDto(deletedNetworkDevice);
    }

    private NetworkDevice findNetworkDevice(Integer id) {
        return networkDeviceRepo.findByIdAndIsActiveIsTrue(id)
                .orElseThrow(() -> new NotFoundException(NETWORK_DEVICE));
    }

    private boolean isDeviceExistsBySerialNo(String serialNumber) {
        return networkDeviceRepo.existsBySerialNumberAndIsActiveIsTrue(serialNumber);
    }
}
