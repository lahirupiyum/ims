package com.lahiru.ims.feature.inventory.asset.fixed;


import com.lahiru.ims.common.service.GenericAssetService;
import com.lahiru.ims.common.service.GenericService;
import com.lahiru.ims.feature.inventory.asset.fixed.dto.FixedAssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.fixed.dto.FixedAssetResponseDto;
import com.lahiru.ims.feature.inventory.location.Location;
import com.lahiru.ims.feature.inventory.location.dto.LocationResponseDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;

import java.util.List;

public interface FixedService extends GenericAssetService<FixedAssetRequestDto, FixedAssetResponseDto> {

}
