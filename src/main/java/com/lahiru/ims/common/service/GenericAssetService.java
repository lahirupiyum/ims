package com.lahiru.ims.common.service;

import com.lahiru.ims.common.dto.feature.AssetRequestDto;
import com.lahiru.ims.common.dto.feature.AssetResponseDto;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;

import java.util.List;

public interface GenericAssetService<RequestDto extends AssetRequestDto, ResponseDto extends AssetResponseDto> extends GenericService<RequestDto, ResponseDto> {
    List<ModelDto> getAllModels() throws Exception;

    List<TypeDto> getAllTypes() throws Exception;

    List<StatusDto> getAllStatus() throws Exception;

    List<ManufacturerDto> getAllManufacturers() throws Exception;

    List<ResponseDto> search(String key) throws Exception;
}
