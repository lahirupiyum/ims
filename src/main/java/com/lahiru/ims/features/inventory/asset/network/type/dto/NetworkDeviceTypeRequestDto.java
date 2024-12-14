package com.lahiru.ims.features.inventory.asset.network.type.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NetworkDeviceTypeRequestDto {
    @NotBlank(message = "Type name is required")
    private String name;
}
