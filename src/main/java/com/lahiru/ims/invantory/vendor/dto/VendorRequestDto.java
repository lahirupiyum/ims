package com.lahiru.ims.invantory.vendor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorRequestDto {
    @NotBlank(message = "Vendor name is required!")
    private String name;
    @NotBlank(message = "Vendor email is required!")
    @Email(message = "Vendor email is not in the correct format")
    private String email;
    @NotBlank(message = "Vendor contact number is required!")
    private String contactNo;
}
