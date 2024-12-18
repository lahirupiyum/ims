package com.lahiru.ims.feature.inventory.asset.fixed;

import com.lahiru.ims.common.controller.GenericAssetController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.common.dto.feature.AssetRequestDto;
import com.lahiru.ims.common.dto.feature.AssetResponseDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import com.lahiru.ims.feature.inventory.vendor.dto.VendorResponseDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("${endpoints.asset-fixed}")
@RequiredArgsConstructor
public class FixedController implements GenericAssetController<AssetRequestDto, AssetResponseDto> {
   private final FixedService service;
    @Override
    public ResponseEntity<PaginationResponse<AssetResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<StandardReponse<List<AssetResponseDto>>> getAll() throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<StandardReponse<AssetResponseDto>> createOne(AssetRequestDto assetRequestDto) throws Exception {
        AssetResponseDto fixedAsset = service.createOne(assetRequestDto);
        return ResponseEntityManager.created(fixedAsset, "Fixed");
    }

    @Override
    public ResponseEntity<StandardReponse<AssetResponseDto>> updateOne(int id, AssetRequestDto assetRequestDto) throws Exception {
        AssetResponseDto assetResponseDto = service.updateOne(id, assetRequestDto);
        return ResponseEntityManager.created(assetResponseDto, "Fixed");
    }

    @Override
    public ResponseEntity<StandardReponse<AssetResponseDto>> deleteOne(int id) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<StandardReponse<List<ModelDto>>> getAllModels() throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<StandardReponse<List<TypeDto>>> getAllTypes() throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<StandardReponse<List<StatusDto>>> getAllStatus() throws Exception {
        return null;
    }
}
