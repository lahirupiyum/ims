package com.lahiru.ims.vendor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorRequestDto {
    @NotEmpty(message = "Vendor name is required!")
    private String name;
    @NotEmpty(message = "Vendor email is required!")
    @Email(message = "Vendor email is not in the correct format")
    private String email;
    @NotEmpty(message = "Vendor contact number is required!")
    private String contactNo;
}
