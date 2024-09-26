package com.lahiru.ims.asset.network.device.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NetworkDeviceRequestDto {
    private String serialNumber;
    private Integer quantity;
    private Integer typeId;
    private Integer manufacturerId;
    private Integer modelId;
    private Integer statusId;
    private Integer vendorId;
    private Integer branchId;
}
