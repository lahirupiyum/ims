package com.lahiru.ims.features.inventory.asset.network.status;

public class NetworkDeviceStatusMapper {
    public static NetworkDeviceStatusResponseDto toDto(NetworkDeviceStatus status) {
        return new NetworkDeviceStatusResponseDto(status.getId(), status.getName());
    }
}
