package com.lahiru.ims.feature.inventory.asset.network;

import com.lahiru.ims.common.repository.AssetRepo;
import com.lahiru.ims.feature.inventory.status.Status;
import com.lahiru.ims.feature.inventory.type.Type;
import com.lahiru.ims.feature.inventory.type.enums.NetworkAssetType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetworkAssetRepo extends AssetRepo<NetworkAsset> {
    @Query("SELECT n FROM NetworkAsset n WHERE n.type = :type")
    List<NetworkAsset> findAllByType(Type type);

    @Query("SELECT n FROM NetworkAsset n WHERE n.type = :type AND n.serialNumber LIKE %:serialNumber%")
    List<NetworkAsset> searchByTypeAndSerialNumber(Type type, String serialNumber);

    @Query("SELECT n FROM NetworkAsset n WHERE n.type = :type AND n.status = :status")
    List<NetworkAsset> findByTypeAndStatus(Type type, Status status);

    @Query("SELECT n FROM NetworkAsset n WHERE n.type = :type AND n.status = :status  AND n.serialNumber LIKE %:serialNumber%")
    List<NetworkAsset> searchByTypeAndStatus(Type type, Status status, String serialNumber);
}
