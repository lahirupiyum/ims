package com.lahiru.ims.feature.customer.router.provideredge.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PERouterRequestDto extends PERouterDto {
    private Integer assetId;
    private Integer networkSwitchId;
}
