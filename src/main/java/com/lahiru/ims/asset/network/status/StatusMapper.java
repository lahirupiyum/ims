package com.lahiru.ims.asset.network.status;

public class StatusMapper {
    public static StatusResponseDto toDto(NetworkDeviceStatus status) {
        return new StatusResponseDto(status.getId(), status.getName());
    }
}
