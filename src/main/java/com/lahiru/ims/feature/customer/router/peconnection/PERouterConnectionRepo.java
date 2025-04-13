package com.lahiru.ims.feature.customer.router.peconnection;

import com.lahiru.ims.common.repository.StatusAwareRepo;
import com.lahiru.ims.feature.inventory.asset.network.NetworkAsset;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PERouterConnectionRepo extends StatusAwareRepo<PERouterConnection> {
    @Query("SELECT COUNT(pe) > 0 FROM PERouterConnection pe WHERE pe.networkAssetSwitch = :networkAsset OR pe.peRouter = :networkAsset")
    Boolean isExistsByNetworkAsset(NetworkAsset networkAsset);
}
