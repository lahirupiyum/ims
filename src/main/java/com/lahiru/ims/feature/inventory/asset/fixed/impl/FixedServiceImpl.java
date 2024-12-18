package com.lahiru.ims.feature.inventory.asset.fixed.impl;


import com.lahiru.ims.common.GenericDao;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.feature.AssetRequestDto;
import com.lahiru.ims.common.dto.feature.AssetResponseDto;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.feature.inventory.asset.fixed.FixedService;
import com.lahiru.ims.feature.inventory.manufacturer.ManufacturerService;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FixedServiceImpl implements FixedService {
    private final ManufacturerService manufacturerService;
    private final GenericDao genericDao;
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

        ManufacturerRequestDto dto = genericDao.checkAndCreate(AssetType.FIXED, assetRequestDto.getManufacturer(), manufacturerService);
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
