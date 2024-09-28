package com.lahiru.ims.asset.network.device.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NetworkDeviceRequestDto {
    @NotBlank(message = "Serial number is required!")
    private String serialNumber;
    private Integer quantity;
    @NotBlank(message = "Device type is required!")
    private Integer typeId;
    @NotBlank(message = "Device manufacturer is required!")
    private Integer manufacturerId;
    @NotBlank(message = "Device model is required!")
    private Integer modelId;
    @NotBlank(message = "Device status is required!")
    private Integer statusId;
    @NotBlank(message = "Vendor is required!")
    private Integer vendorId;
    @NotBlank(message = "Branch is required!")
    private Integer branchId;
}
