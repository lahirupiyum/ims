package com.lahiru.ims.feature.customer.router.customer.dto;

import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CusRouterResponseDto extends CusRouterDto {
    private Integer id;
    private NetworkAssetResponseDto asset;
}
