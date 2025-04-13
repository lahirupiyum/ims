package com.lahiru.ims.feature.inventory.asset.mobile;


import com.lahiru.ims.common.service.EntityFinderService;
import com.lahiru.ims.common.service.GenericAssetService;
import com.lahiru.ims.common.service.ModelMapperService;
import com.lahiru.ims.feature.inventory.asset.mobile.dto.MobileAssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.mobile.dto.MobileAssetResponseDto;

public interface MobileAssetService extends GenericAssetService<MobileAssetRequestDto, MobileAssetResponseDto>,
        ModelMapperService<MobileAsset, MobileAssetRequestDto, MobileAssetResponseDto>,
        EntityFinderService<MobileAsset> {
}
