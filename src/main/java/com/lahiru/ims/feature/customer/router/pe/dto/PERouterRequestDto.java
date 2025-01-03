package com.lahiru.ims.feature.customer.router.pe.dto;

import com.lahiru.ims.common.dto.feature.BasicInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PERouterRequestDto extends BasicInfo {
    private Integer assetId;
}
