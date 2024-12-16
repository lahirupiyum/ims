package com.lahiru.ims.invantory.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetworkDeviceModelRequestDto {
    @NotBlank(message = "Model name is required!")
    private String name;
}
