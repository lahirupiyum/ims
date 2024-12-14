package com.lahiru.ims.features.inventory.asset.network.device.dto;

import com.lahiru.ims.features.inventory.asset.network.manufacturer.dto.NetworkDeviceManufacturerResponseDto;
import com.lahiru.ims.features.inventory.asset.network.model.dto.NetworkDeviceModelResponseDto;
import com.lahiru.ims.features.inventory.asset.network.status.NetworkDeviceStatusResponseDto;
import com.lahiru.ims.features.inventory.asset.network.type.dto.NetworkDeviceTypeResponseDto;
import com.lahiru.ims.features.inventory.location.dto.LocationResponseDto;
import com.lahiru.ims.features.inventory.vendor.dto.VendorResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class NetworkDeviceResponseDto {
    private Integer id;
    private String serialNumber;
    private Integer quantity;

    private NetworkDeviceTypeResponseDto type;
    private NetworkDeviceManufacturerResponseDto manufacturer;
    private NetworkDeviceModelResponseDto model;
    private NetworkDeviceStatusResponseDto status;

    private LocationResponseDto branch;
    private VendorResponseDto vendor;

}
