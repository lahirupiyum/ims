package com.lahiru.ims.branch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchResponseDto {
    private Integer id;
    private String name;
    private String address;
}
