package com.lahiru.ims.branch.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchRequestDto {
    @NotBlank(message = "Branch name is required!")
    private String name;
    @NotBlank(message = "Branch address is required")
    private String address;
}
