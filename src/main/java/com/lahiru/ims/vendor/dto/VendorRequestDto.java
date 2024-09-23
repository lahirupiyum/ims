package com.lahiru.ims.vendor.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VendorRequestDto {
    private String name;
    private String email;
    private String contactNo;
}
