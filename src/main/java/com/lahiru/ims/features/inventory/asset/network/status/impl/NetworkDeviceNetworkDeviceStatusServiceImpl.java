package com.lahiru.ims.features.inventory.asset.network.status.impl;

import com.lahiru.ims.features.inventory.asset.network.status.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NetworkDeviceNetworkDeviceStatusServiceImpl implements NetworkDeviceStatusService {

    private final NetworkDeviceStatusRepo statusRepo;

    @Override
    public List<NetworkDeviceStatusResponseDto> findAll() throws Exception {
        List<NetworkDeviceStatus> all = statusRepo.findAll();
        return all.stream().map(NetworkDeviceStatusMapper::toDto).toList();
    }
}
