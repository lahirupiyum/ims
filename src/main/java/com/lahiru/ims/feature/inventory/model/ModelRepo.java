package com.lahiru.ims.feature.inventory.model;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.feature.inventory.status.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepo extends JpaRepository<Model,Integer> {
    List<Model> findAllByAssetType(AssetType assetType);
}
