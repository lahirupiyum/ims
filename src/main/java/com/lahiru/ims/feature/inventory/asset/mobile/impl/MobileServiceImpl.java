package com.lahiru.ims.feature.inventory.asset.mobile.impl;


import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.feature.AssetRequestDto;
import com.lahiru.ims.common.dto.feature.AssetResponseDto;
import com.lahiru.ims.feature.inventory.asset.mobile.Mobile;
import com.lahiru.ims.feature.inventory.asset.mobile.MobileService;
import com.lahiru.ims.feature.inventory.asset.mobile.dto.MobileAssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.mobile.dto.MobileAssetResponseDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MobileServiceImpl implements MobileService {

    @Override
    public Mobile findOne(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<ModelDto> getAllModels() throws Exception {
        return List.of();
    }

    @Override
    public List<TypeDto> getAllTypes() throws Exception {
        return List.of();
    }

    @Override
    public List<StatusDto> getAllStatus() throws Exception {
        return List.of();
    }

    @Override
    public PaginationResponse<MobileAssetResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        return null;
    }

    @Override
    public List<MobileAssetResponseDto> findAll() throws Exception {
        return List.of();
    }

    @Override
    public MobileAssetResponseDto createOne(MobileAssetRequestDto mobileAssetRequestDto) throws Exception {
        return null;
    }

    @Override
    public MobileAssetResponseDto updateOne(int id, MobileAssetRequestDto mobileAssetRequestDto) throws Exception {
        return null;
    }

    @Override
    public MobileAssetResponseDto deleteOne(int id) throws Exception {
        return null;
    }

    @Override
    public Mobile convertToModel(MobileAssetRequestDto mobileAssetRequestDto) throws Exception {
        return null;
    }

    @Override
    public MobileAssetResponseDto convertToDto(Mobile mobile) throws Exception {
        return null;
    }
}
