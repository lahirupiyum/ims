package com.lahiru.ims.branch;

import com.lahiru.ims.branch.dto.BranchRequestDto;
import com.lahiru.ims.branch.dto.BranchResponseDto;

public class BranchMapper {
    public static BranchResponseDto toDto(Branch branch) {
        return new BranchResponseDto(branch.getId(), branch.getName(), branch.getAddress());
    }

    public static Branch toModel(BranchRequestDto requestDto) {
        return new Branch(requestDto.getName(), requestDto.getAddress());
    }
}
