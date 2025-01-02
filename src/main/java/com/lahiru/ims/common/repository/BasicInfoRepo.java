package com.lahiru.ims.common.repository;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.common.model.BasicInfoAudit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BasicInfoRepo <Model extends BasicInfoAudit> extends IDNameRepo<Model>{
    @Query("SELECT e FROM #{#entityName} e WHERE e.assetType = :assetType")
    List<Model> findAllByAssetType(AssetType assetType);
}
