package com.lahiru.ims.feature.inventory.asset.network;


import com.lahiru.ims.common.service.GenericService;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetResponseDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;

import java.util.List;

public interface NetworkService extends GenericService<NetworkAssetRequestDto, NetworkAssetResponseDto> {
    List<ModelDto> getAllModel() throws Exception;

    List<TypeDto> getAllType() throws Exception;

    List<StatusDto> getAllStatus() throws Exception;
}
