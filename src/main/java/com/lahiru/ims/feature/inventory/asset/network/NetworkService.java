package com.lahiru.ims.feature.inventory.asset.network;

import com.lahiru.ims.common.service.EntityFinderService;
import com.lahiru.ims.common.service.GenericAssetService;
import com.lahiru.ims.common.service.ModelMapperService;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetResponseDto;
import com.lahiru.ims.feature.inventory.status.enums.NetworkAssetStatus;

import java.util.List;

public interface NetworkService extends
        GenericAssetService<NetworkAssetRequestDto, NetworkAssetResponseDto>,
        ModelMapperService<Network, NetworkAssetRequestDto, NetworkAssetResponseDto>,
        EntityFinderService<Network> {

        List<NetworkAssetResponseDto> findAllPERouters() throws Exception;
        List<NetworkAssetResponseDto> findAllSwitches() throws Exception;
        List<NetworkAssetResponseDto> findAllRouters() throws Exception;
        List<NetworkAssetResponseDto> searchSwitches(String serialNumber) throws Exception;
        List<NetworkAssetResponseDto> searchRouters(String serialNumber) throws  Exception;

        Network updateAssetStatus(Integer id, NetworkAssetStatus status) throws Exception;
        Network updateAssetStatus(Network networkAsset, NetworkAssetStatus status) throws Exception;
}
