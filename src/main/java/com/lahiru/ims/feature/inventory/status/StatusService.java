package com.lahiru.ims.feature.inventory.status;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.common.service.GenericBasicInfoService;
import com.lahiru.ims.feature.inventory.status.enums.NetworkAssetStatus;

public interface StatusService extends GenericBasicInfoService<Status> {
    Status getByStatus(AssetType assetType, NetworkAssetStatus networkAssetStatus);
}
