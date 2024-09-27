package com.lahiru.ims.asset.network.device.dto;

import com.lahiru.ims.asset.network.manufacturer.dto.NetworkDeviceManufacturerResponseDto;
import com.lahiru.ims.asset.network.model.dto.NetworkDeviceModelResponseDto;
import com.lahiru.ims.asset.network.status.NetworkDeviceStatusResponseDto;
import com.lahiru.ims.asset.network.type.dto.NetworkDeviceTypeResponseDto;
import com.lahiru.ims.branch.dto.BranchResponseDto;
import com.lahiru.ims.vendor.dto.VendorResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private BranchResponseDto branch;
    private VendorResponseDto vendor;

}
