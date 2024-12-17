package com.lahiru.ims.common.service;

import com.lahiru.ims.common.dto.feature.AssetRequestDto;
import com.lahiru.ims.common.dto.feature.AssetResponseDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelResponseDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusResponseDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeResponseDto;

import java.util.List;

public interface GenericAssetService<RequestDto extends AssetRequestDto, ResponseDto extends AssetResponseDto> extends GenericService<RequestDto, ResponseDto> {
    List<ModelResponseDto> getAllModels() throws Exception;

    List<TypeResponseDto> getAllTypes() throws Exception;

    List<StatusResponseDto> getAllStatus() throws Exception;
}
