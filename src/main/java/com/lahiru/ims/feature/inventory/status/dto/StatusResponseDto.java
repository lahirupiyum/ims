package com.lahiru.ims.feature.inventory.status.dto;

import com.lahiru.ims.common.dto.feature.BasicInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class StatusResponseDto extends BasicInfo {
    public StatusResponseDto(int id, String name) {
        super(id, name);
    }
}
