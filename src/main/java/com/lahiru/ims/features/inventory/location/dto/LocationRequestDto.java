package com.lahiru.ims.features.inventory.location.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequestDto {
    @NotBlank(message = "Branch name is required!")
    private String name;
    @NotBlank(message = "Branch address is required")
    private String address;
}
