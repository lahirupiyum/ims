package com.lahiru.ims.feature.customer.router.peconnection.dto;

import com.lahiru.ims.feature.customer.router.pe.dto.PERouterResponseDto;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PERouterConnectionResponseDto extends PERouterConnectionDto {
    private Integer id;
    private PERouterResponseDto peRouter;
    private NetworkAssetResponseDto networkSwitch;
}
