package com.lahiru.ims.features.inventory.asset.network.status;

import java.util.List;

public interface NetworkDeviceStatusService {
    List<NetworkDeviceStatusResponseDto> findAll() throws Exception;
}
