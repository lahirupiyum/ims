package com.lahiru.ims.features.inventory.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponseDto {
    private Integer id;
    private String name;
    private String address;
}