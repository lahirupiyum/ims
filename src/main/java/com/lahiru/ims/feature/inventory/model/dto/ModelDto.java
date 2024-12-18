package com.lahiru.ims.feature.inventory.model.dto;

import com.lahiru.ims.common.dto.feature.BasicInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ModelDto extends BasicInfo {
    public ModelDto(int id, String name) {
        super(id, name);
    }
}
