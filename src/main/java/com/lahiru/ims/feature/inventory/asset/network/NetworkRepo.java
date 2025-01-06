package com.lahiru.ims.feature.inventory.asset.network;

import com.lahiru.ims.common.repository.AssetRepo;
import com.lahiru.ims.feature.inventory.type.Type;
import io.swagger.v3.oas.annotations.Webhook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetworkRepo extends AssetRepo<Network> {
    @Query("SELECT n FROM Network n WHERE n.type = :type")
    List<Network> findAllByType(Type type);

    @Query("SELECT n FROM Network n WHERE n.type = :type AND n.serialNumber LIKE %:serialNumber%")
    List<Network> searchByTypeAndSerialNumber(Type type, String serialNumber);
}
