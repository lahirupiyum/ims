package com.lahiru.ims.asset.network.status;

public class NetworkDeviceStatusMapper {
    public static NetworkDeviceStatusResponseDto toDto(NetworkDeviceStatus status) {
        return new NetworkDeviceStatusResponseDto(status.getId(), status.getName());
    }
}
