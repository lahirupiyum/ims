package com.lahiru.ims.feature.inventory.asset.mobile;


import com.lahiru.ims.common.controller.GenericAssetController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.feature.inventory.asset.mobile.dto.MobileAssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.mobile.dto.MobileAssetResponseDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("${endpoints.asset-mobile}")
@RequiredArgsConstructor
@RestController
public class MobileController implements GenericAssetController<MobileAssetRequestDto, MobileAssetResponseDto> {
    private final MobileService service;

    @Override
    public ResponseEntity<PaginationResponse<MobileAssetResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        PaginationResponse<MobileAssetResponseDto> mobileByPageWise = service.findByPageWise(page, pageSize);

        return ResponseEntityManager.page(mobileByPageWise);
    }

    @Override
    public ResponseEntity<StandardReponse<List<MobileAssetResponseDto>>> getAll() throws Exception {
        List<MobileAssetResponseDto> allMobiles = service.findAll();
        return ResponseEntityManager.ok(allMobiles);
    }

    @Override
    public ResponseEntity<StandardReponse<MobileAssetResponseDto>> createOne(MobileAssetRequestDto mobileAssetRequestDto) throws Exception {
        MobileAssetResponseDto mobileSaved = service.createOne(mobileAssetRequestDto);
        return ResponseEntityManager.created(mobileSaved, "Mobile");
    }

    @Override
    public ResponseEntity<StandardReponse<MobileAssetResponseDto>> updateOne(int id, MobileAssetRequestDto mobileAssetRequestDto) throws Exception {
        MobileAssetResponseDto mobileUpdated = service.updateOne(id, mobileAssetRequestDto);
        return ResponseEntityManager.ok(mobileUpdated);
    }

    @Override
    public ResponseEntity<StandardReponse<MobileAssetResponseDto>> deleteOne(int id) throws Exception {
        MobileAssetResponseDto mobileDelete = service.deleteOne(id);
        return ResponseEntityManager.ok(mobileDelete);
    }

    @Override
    public ResponseEntity<StandardReponse<List<ModelDto>>> getAllModels() throws Exception {
        List<ModelDto> allModels = service.getAllModels();
        return ResponseEntityManager.ok(allModels);
    }

    @Override
    public ResponseEntity<StandardReponse<List<TypeDto>>> getAllTypes() throws Exception {
        List<TypeDto> allTypes = service.getAllTypes();
        return ResponseEntityManager.ok(allTypes);
    }

    @Override
    public ResponseEntity<StandardReponse<List<StatusDto>>> getAllStatus() throws Exception {
        List<StatusDto> allStatus = service.getAllStatus();
        return ResponseEntityManager.ok(allStatus);
    }
}
