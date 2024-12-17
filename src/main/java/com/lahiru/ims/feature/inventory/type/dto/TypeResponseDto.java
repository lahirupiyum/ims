package com.lahiru.ims.feature.inventory.type.dto;

import com.lahiru.ims.common.dto.feature.BasicInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TypeResponseDto extends BasicInfo {
    public TypeResponseDto(int id, String name) {
        super(id, name);
    }
}
