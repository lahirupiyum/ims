package com.lahiru.ims.features.inventory.asset.network.manufacturer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NetworkDeviceManufacturerRequestDto {
    @NotBlank(message = "Manufacturer name is required!")
    private String name;
}
