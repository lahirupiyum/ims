package com.lahiru.ims.feature.inventory.status;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.feature.inventory.type.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StatusRepo extends JpaRepository<Status,Integer> {
    List<Status> findAllByAssetType(AssetType assetType);
}
