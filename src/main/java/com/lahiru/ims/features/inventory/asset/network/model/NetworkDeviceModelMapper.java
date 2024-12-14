package com.lahiru.ims.features.inventory.asset.network.model;

import com.lahiru.ims.features.inventory.asset.network.model.dto.NetworkDeviceModelRequestDto;
import com.lahiru.ims.features.inventory.asset.network.model.dto.NetworkDeviceModelResponseDto;

public class NetworkDeviceModelMapper {
    public static NetworkDeviceModelResponseDto toDto(NetworkDeviceModel deviceModel) {
        return new NetworkDeviceModelResponseDto(deviceModel.getId(), deviceModel.getName());
    }

    public static NetworkDeviceModel toModel(NetworkDeviceModelRequestDto requestDto) {
        return new NetworkDeviceModel(requestDto.getName());
    }
}
