package com.lahiru.ims.feature.customer.router.customer.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CusRouterRequestDto extends CusRouterDto {
    private Integer assetId;
}
