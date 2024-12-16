package com.lahiru.ims.invantory.model;

import com.lahiru.ims.invantory.model.dto.NetworkDeviceModelRequestDto;
import com.lahiru.ims.invantory.model.dto.NetworkDeviceModelResponseDto;

public class NetworkDeviceModelMapper {
    public static NetworkDeviceModelResponseDto toDto(Model deviceModel) {
        return new NetworkDeviceModelResponseDto(deviceModel.getId(), deviceModel.getName());
    }

    public static Model toModel(NetworkDeviceModelRequestDto requestDto) {
        return new Model();
    }
}
