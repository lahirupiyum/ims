package com.lahiru.ims.feature.customer.router.peconnection.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PERouterConnectionRequestDto extends PERouterConnectionDto {
    private Integer peRouterId;
    private Integer networkSwitchId;
}
