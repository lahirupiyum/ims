package com.lahiru.ims.asset.network.status.impl;

import com.lahiru.ims.asset.network.status.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusRepo statusRepo;

    @Override
    public List<StatusResponseDto> findAll() throws Exception {
        List<NetworkDeviceStatus> all = statusRepo.findAll();
        return all.stream().map(StatusMapper::toDto).toList();
    }
}
