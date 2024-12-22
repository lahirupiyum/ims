package com.lahiru.ims.feature.inventory.asset.network;


import com.lahiru.ims.common.controller.GenericAssetController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetResponseDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("${endpoints.asset-network}")
@RestController
@RequiredArgsConstructor
public class NetworkController implements GenericAssetController<NetworkAssetRequestDto, NetworkAssetResponseDto> {
    private final NetworkService service;

    @Override
    public ResponseEntity<PaginationResponse<NetworkAssetResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        PaginationResponse<NetworkAssetResponseDto> networkByPageWise = service.findByPageWise(page, pageSize);
        return ResponseEntityManager.page(networkByPageWise);
    }

    @Override
    public ResponseEntity<StandardReponse<List<NetworkAssetResponseDto>>> getAll() throws Exception {
        List<NetworkAssetResponseDto> allNetwork = service.findAll();
        return ResponseEntityManager.ok(allNetwork);
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkAssetResponseDto>> createOne(NetworkAssetRequestDto networkAssetRequestDto) throws Exception {
        NetworkAssetResponseDto network = service.createOne(networkAssetRequestDto);
        return ResponseEntityManager.created(network,"Network");
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkAssetResponseDto>> updateOne(int id, NetworkAssetRequestDto networkAssetRequestDto) throws Exception {
        NetworkAssetResponseDto networkAssetResponseDto = service.updateOne(id, networkAssetRequestDto);
        return ResponseEntityManager.ok(networkAssetResponseDto);
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkAssetResponseDto>> deleteOne(int id) throws Exception {
        NetworkAssetResponseDto networkAssetResponseDto = service.deleteOne(id);
        return ResponseEntityManager.ok(networkAssetResponseDto);
    }

    @Override
    public ResponseEntity<StandardReponse<List<ModelDto>>> getAllModels() throws Exception {
        List<ModelDto> modelList = service.getAllModels();
        return ResponseEntityManager.ok(modelList);
    }

    @Override
    public ResponseEntity<StandardReponse<List<TypeDto>>> getAllTypes() throws Exception {
        List<TypeDto> typeList = service.getAllTypes();
        return ResponseEntityManager.ok(typeList);
    }

    @Override
    public ResponseEntity<StandardReponse<List<StatusDto>>> getAllStatus() throws Exception {
        List<StatusDto> statusList = service.getAllStatus();
        return ResponseEntityManager.ok(statusList);
    }
}
