package com.lahiru.ims.feature.inventory.asset.mobile;


import com.lahiru.ims.common.controller.GenericController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.common.dto.feature.AssetRequestDto;
import com.lahiru.ims.common.dto.feature.AssetResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class MobileController implements GenericController<AssetRequestDto, AssetResponseDto> {
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
        return null;
    }

    @Override
    public ResponseEntity<StandardReponse<AssetResponseDto>> updateOne(int id, AssetRequestDto assetRequestDto) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<StandardReponse<AssetResponseDto>> deleteOne(int id) throws Exception {
        return null;
    }
}
