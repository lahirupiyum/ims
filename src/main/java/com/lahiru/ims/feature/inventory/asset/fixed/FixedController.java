package com.lahiru.ims.feature.inventory.asset.fixed;

import com.lahiru.ims.common.controller.GenericAssetController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.feature.inventory.asset.fixed.dto.FixedAssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.fixed.dto.FixedAssetResponseDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("${endpoints.asset-fixed}")
@RequiredArgsConstructor
public class FixedController implements GenericAssetController<FixedAssetRequestDto, FixedAssetResponseDto> {
   private final FixedService service;

    @Override
    public ResponseEntity<StandardReponse<List<ModelDto>>> getAllModels() throws Exception {
        List<ModelDto> modelList = service.getAllModel();
        return ResponseEntityManager.ok(modelList);
    }

    @Override
    public ResponseEntity<StandardReponse<List<TypeDto>>> getAllTypes() throws Exception {
        List<TypeDto> typeList = service.getAllType();
        return ResponseEntityManager.ok(typeList);
    }

    @Override
    public ResponseEntity<StandardReponse<List<StatusDto>>> getAllStatus() throws Exception {
        List<StatusDto> statusList = service.getAllStatus();
        return ResponseEntityManager.ok(statusList);
    }

    @Override
    public ResponseEntity<PaginationResponse<FixedAssetResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        PaginationResponse<FixedAssetResponseDto> fixedPage = service.findByPageWise(page, pageSize);
        return ResponseEntityManager.page(fixedPage);
    }

    @Override
    public ResponseEntity<StandardReponse<List<FixedAssetResponseDto>>> getAll() throws Exception {
        List<FixedAssetResponseDto> fixedList = service.findAll();
        return ResponseEntityManager.ok(fixedList);
    }

    @Override
    public ResponseEntity<StandardReponse<FixedAssetResponseDto>> createOne(FixedAssetRequestDto fixedAssetRequestDto) throws Exception {
        FixedAssetResponseDto fixedAsset = service.createOne(fixedAssetRequestDto);
        return ResponseEntityManager.created(fixedAsset, "Fixed");
    }

    @Override
    public ResponseEntity<StandardReponse<FixedAssetResponseDto>> updateOne(int id, FixedAssetRequestDto fixedAssetRequestDto) throws Exception {
        FixedAssetResponseDto assetResponseDto = service.updateOne(id, fixedAssetRequestDto);
        return ResponseEntityManager.created(assetResponseDto, "Fixed");
    }

    @Override
    public ResponseEntity<StandardReponse<FixedAssetResponseDto>> deleteOne(int id) throws Exception {
        FixedAssetResponseDto fixedAssetResponseDto = service.deleteOne(id);
        return ResponseEntityManager.ok(fixedAssetResponseDto);
    }


}
