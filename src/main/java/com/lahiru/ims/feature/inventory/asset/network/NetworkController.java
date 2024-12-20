package com.lahiru.ims.feature.inventory.asset.network;


import com.lahiru.ims.common.controller.GenericController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.common.dto.feature.AssetRequestDto;
import com.lahiru.ims.common.dto.feature.AssetResponseDto;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetResponseDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("${endpoints.asset-network}")
@RestController
@RequiredArgsConstructor
public class NetworkController implements GenericController<NetworkAssetRequestDto, NetworkAssetResponseDto> {
    private final NetworkService service;

    @Override
    public ResponseEntity<PaginationResponse<NetworkAssetResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<StandardReponse<List<NetworkAssetResponseDto>>> getAll() throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkAssetResponseDto>> createOne(NetworkAssetRequestDto networkAssetRequestDto) throws Exception {
        NetworkAssetResponseDto network = service.createOne(networkAssetRequestDto);
        return ResponseEntityManager.created(network,"Network");
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkAssetResponseDto>> updateOne(int id, NetworkAssetRequestDto networkAssetRequestDto) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkAssetResponseDto>> deleteOne(int id) throws Exception {
        return null;
    }
}
