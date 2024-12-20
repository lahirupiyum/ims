package com.lahiru.ims.feature.inventory.asset.network.impl;


import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.feature.inventory.asset.network.NetworkRepo;
import com.lahiru.ims.feature.inventory.asset.network.NetworkService;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NetworkServiceImpl implements NetworkService {
    private final NetworkRepo repository;
    private final String NETWORK = "Network";

    @Override
    public PaginationResponse<NetworkAssetResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        return null;
    }

    @Override
    public List<NetworkAssetResponseDto> findAll() throws Exception {

        return List.of();
    }

    @Override
    public NetworkAssetResponseDto createOne(NetworkAssetRequestDto networkAssetRequestDto) throws Exception {
        return null;
    }

    @Override
    public NetworkAssetResponseDto updateOne(int id, NetworkAssetRequestDto networkAssetRequestDto) throws Exception {
        return null;
    }

    @Override
    public NetworkAssetResponseDto deleteOne(int id) throws Exception {
        return null;
    }
}
