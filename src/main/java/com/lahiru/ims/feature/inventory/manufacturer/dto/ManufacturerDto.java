package com.lahiru.ims.feature.inventory.manufacturer.dto;

import com.lahiru.ims.common.dto.feature.BasicInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ManufacturerDto extends BasicInfo {
    public ManufacturerDto(int id, String name) {
        super(id, name);
    }
}
