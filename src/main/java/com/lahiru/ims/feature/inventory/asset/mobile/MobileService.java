package com.lahiru.ims.feature.inventory.asset.mobile;


import com.lahiru.ims.common.service.EntityFinderService;
import com.lahiru.ims.common.service.GenericAssetService;
import com.lahiru.ims.common.service.GenericService;
import com.lahiru.ims.common.dto.feature.AssetRequestDto;
import com.lahiru.ims.common.dto.feature.AssetResponseDto;
import com.lahiru.ims.common.service.ModelMapperService;
import com.lahiru.ims.feature.inventory.asset.mobile.dto.MobileAssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.mobile.dto.MobileAssetResponseDto;

public interface MobileService extends GenericAssetService<MobileAssetRequestDto, MobileAssetResponseDto>,
        ModelMapperService<Mobile, MobileAssetRequestDto, MobileAssetResponseDto>,
        EntityFinderService<Mobile> {
}
