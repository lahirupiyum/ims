package com.lahiru.ims.invantory.asset.network.impl;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.invantory.asset.dto.AssetRequestDto;
import com.lahiru.ims.invantory.asset.dto.AssetResponseDto;
import com.lahiru.ims.invantory.asset.network.NetworkService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NetworkServiceImpl implements NetworkService {
    @Override
    public PaginationResponse<AssetResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        return null;
    }

    @Override
    public List<AssetResponseDto> findAll() throws Exception {
        return List.of();
    }

    @Override
    public AssetResponseDto createOne(AssetRequestDto assetRequestDto) throws Exception {
        return null;
    }

    @Override
    public AssetResponseDto updateOne(int id, AssetRequestDto assetRequestDto) throws Exception {
        return null;
    }

    @Override
    public AssetResponseDto deleteOne(int id) throws Exception {
        return null;
    }
}
