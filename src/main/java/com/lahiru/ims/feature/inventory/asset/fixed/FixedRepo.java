package com.lahiru.ims.feature.inventory.asset.fixed;

import com.lahiru.ims.common.repository.AssetRepo;
import com.lahiru.ims.common.repository.StatusAwareRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedRepo extends AssetRepo<Fixed> {
}
