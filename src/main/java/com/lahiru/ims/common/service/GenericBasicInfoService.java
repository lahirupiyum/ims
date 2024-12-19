package com.lahiru.ims.common.service;

import com.lahiru.ims.common.dto.feature.BasicInfo;
import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.common.model.BasicInfoAudit;

import java.util.List;

public interface GenericBasicInfoService <Model extends BasicInfoAudit> {
    Model createOne(String name, AssetType assetType) throws Exception;
    Model findOne(Integer id) throws  Exception;
    List<Model> getAll(AssetType assetType) throws Exception;
}
