package com.lahiru.ims.asset.network.type;

import com.lahiru.ims.asset.network.type.dto.NetworkDeviceTypeRequestDto;
import com.lahiru.ims.asset.network.type.dto.NetworkDeviceTypeResponseDto;

public class NetworkDeviceTypeMapper {
    public static NetworkDeviceType toModel(NetworkDeviceTypeRequestDto requestDto) {
        NetworkDeviceType networkDeviceType = new NetworkDeviceType();
        networkDeviceType.setName(requestDto.getName());
        return networkDeviceType;
    }

    public static NetworkDeviceTypeResponseDto toDto(NetworkDeviceType networkDeviceType) {
        return new NetworkDeviceTypeResponseDto(networkDeviceType.getId(), networkDeviceType.getName());
    }
}
