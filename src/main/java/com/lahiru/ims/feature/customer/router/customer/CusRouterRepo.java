package com.lahiru.ims.feature.customer.router.customer;

import com.lahiru.ims.common.repository.StatusAwareRepo;
import com.lahiru.ims.feature.inventory.asset.network.NetworkAsset;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CusRouterRepo extends StatusAwareRepo<CusRouter> {
    @Query("SELECT COUNT(cr) > 0 FROM CusRouter cr WHERE cr.networkAsset = :networkAsset AND cr.isActive = true")
    Boolean isOneExistsByAsset(NetworkAsset networkAsset);
}
