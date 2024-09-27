package com.lahiru.ims.asset.network.manufacturer;

import com.lahiru.ims.asset.network.manufacturer.dto.NetworkDeviceManufacturerRequestDto;
import com.lahiru.ims.asset.network.manufacturer.dto.NetworkDeviceManufacturerResponseDto;

public class NetworkDeviceManufacturerMapper {
    public static NetworkDeviceManufacturerResponseDto toDto(NetworkDeviceManufacturer manufacturer) {
        return new NetworkDeviceManufacturerResponseDto(manufacturer.getId(), manufacturer.getName());
    }

    public static NetworkDeviceManufacturer toModel (NetworkDeviceManufacturerRequestDto requestDto) {
        return new NetworkDeviceManufacturer(requestDto.getName());
    }
}
