package com.lahiru.ims.feature.inventory.type.dto;

import com.lahiru.ims.common.dto.feature.BasicInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TypeDto extends BasicInfo {
    public TypeDto(int id, String name) {
        super(id, name);
    }
}
