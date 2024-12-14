package com.lahiru.ims.features.inventory.asset.network.device.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NetworkDeviceRequestDto {
    @NotBlank(message = "Serial number is required!")
    private String serialNumber;
    private Integer quantity;
    @Min(value=1, message = "Device type is required!")
    private Integer typeId;
    @Min(value=1, message = "Device manufacturer is required!")
    private Integer manufacturerId;
    @Min(value=1, message = "Device model is required!")
    private Integer modelId;
    @Min(value=1, message = "Device status is required!")
    private Integer statusId;
    @Min(value=1, message = "Vendor is required!")
    private Integer vendorId;
    @Min(value=1, message = "Branch is required!")
    private Integer branchId;
}
