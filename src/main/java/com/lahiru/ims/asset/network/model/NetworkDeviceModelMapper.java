package com.lahiru.ims.asset.network.model;

import com.lahiru.ims.asset.network.model.dto.DeviceModelRequestDto;
import com.lahiru.ims.asset.network.model.dto.DeviceModelResponseDto;

public class NetworkDeviceModelMapper {
    public static DeviceModelResponseDto toDto(NetworkDeviceModel deviceModel) {
        return new DeviceModelResponseDto(deviceModel.getId(), deviceModel.getName());
    }

    public static NetworkDeviceModel toModel(DeviceModelRequestDto requestDto) {
        return new NetworkDeviceModel(requestDto.getName());
    }
}
