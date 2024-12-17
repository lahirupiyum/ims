package com.lahiru.ims.common.service;

import com.lahiru.ims.common.dto.feature.BasicInfo;
import com.lahiru.ims.common.enums.AssetType;

import java.util.List;

public interface GenericBasicInfoService <ResponseDto extends BasicInfo> {
    ResponseDto createOne(String name) throws Exception;
    List<ResponseDto> getAll(AssetType assetType) throws Exception;
}
