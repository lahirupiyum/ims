package com.lahiru.ims.feature.inventory.model;


import com.lahiru.ims.common.GenericController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.feature.inventory.model.dto.ModelRequestDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ModelController implements GenericController<ModelRequestDto, ModelResponseDto> {
    @Override
    public ResponseEntity<PaginationResponse<ModelResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<StandardReponse<List<ModelResponseDto>>> getAll() throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<StandardReponse<ModelResponseDto>> createOne(ModelRequestDto modelRequestDto) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<StandardReponse<ModelResponseDto>> updateOne(int id, ModelRequestDto modelRequestDto) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<StandardReponse<ModelResponseDto>> deleteOne(int id) throws Exception {
        return null;
    }
}
