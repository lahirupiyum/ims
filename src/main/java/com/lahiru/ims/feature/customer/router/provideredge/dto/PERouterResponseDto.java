package com.lahiru.ims.feature.customer.router.provideredge.dto;

import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PERouterResponseDto extends PERouterDto {
    private Integer id;
    private NetworkAssetResponseDto asset;
    private NetworkAssetResponseDto networkSwitch;
}
