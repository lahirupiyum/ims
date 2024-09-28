package com.lahiru.ims.asset.network.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NetworkDeviceModelRequestDto {
    @NotBlank(message = "Model name is required!")
    private String name;
}
