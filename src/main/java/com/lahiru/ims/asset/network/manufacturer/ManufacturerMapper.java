package com.lahiru.ims.asset.network.manufacturer;

import com.lahiru.ims.asset.network.manufacturer.dto.ManufacturerRequestDto;
import com.lahiru.ims.asset.network.manufacturer.dto.ManufacturerResponseDto;

public class ManufacturerMapper {
    public static ManufacturerResponseDto toDto(NetworkDeviceManufacturer manufacturer) {
        return new ManufacturerResponseDto(manufacturer.getId(), manufacturer.getName());
    }

    public static NetworkDeviceManufacturer toModel (ManufacturerRequestDto requestDto) {
        return new NetworkDeviceManufacturer(requestDto.getName());
    }
}
