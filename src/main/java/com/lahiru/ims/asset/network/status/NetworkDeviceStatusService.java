package com.lahiru.ims.asset.network.status;

import java.util.List;

public interface NetworkDeviceStatusService {
    List<NetworkDeviceStatusResponseDto> findAll() throws Exception;
}
