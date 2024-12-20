package com.lahiru.ims.feature.inventory.asset.network;


import com.lahiru.ims.common.service.GenericService;
import com.lahiru.ims.common.dto.feature.AssetResponseDto;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetResponseDto;

public interface NetworkService extends GenericService<NetworkAssetRequestDto, NetworkAssetResponseDto> {
}
