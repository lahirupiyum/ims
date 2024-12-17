package com.lahiru.ims.feature.inventory.asset.fixed;



import com.lahiru.ims.common.GenericController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.feature.inventory.asset.dto.AssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.dto.AssetResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class FixedController implements GenericController<AssetRequestDto, AssetResponseDto> {
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
