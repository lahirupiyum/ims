package com.lahiru.ims.feature.inventory.manufacturer.dto;

import com.lahiru.ims.common.dto.feature.BasicInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ManufacturerRequestDto extends BasicInfo {
    public ManufacturerRequestDto(int id, String name) {
        super(id, name);
    }
}
