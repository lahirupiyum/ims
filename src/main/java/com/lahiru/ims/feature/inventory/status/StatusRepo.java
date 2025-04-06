package com.lahiru.ims.feature.inventory.status;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.common.repository.BasicInfoRepo;
import com.lahiru.ims.feature.inventory.type.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepo extends BasicInfoRepo<Status> {
    @Query("SELECT s FROM Status s WHERE s.assetType = :assetType and s.name = :status")
    Optional<Status> findAllByAssetTypeAndStatus(AssetType assetType, String status);
}
