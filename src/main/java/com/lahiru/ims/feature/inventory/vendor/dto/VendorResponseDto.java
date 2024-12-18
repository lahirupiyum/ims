package com.lahiru.ims.feature.inventory.vendor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorResponseDto {
    private Integer id;
    private String name;
    private String email;
    private String contactNo;
}
