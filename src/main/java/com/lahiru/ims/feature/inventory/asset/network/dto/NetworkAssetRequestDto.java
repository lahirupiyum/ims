package com.lahiru.ims.feature.inventory.asset.network.dto;

import com.lahiru.ims.common.dto.feature.AssetRequestDto;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class NetworkAssetRequestDto extends AssetRequestDto {
    public NetworkAssetRequestDto(String assetNumber, String serialNumber, ManufacturerDto manufacturer, TypeDto type, ModelDto model, Integer statusId, Integer vendorId, Integer locationId) {
        super(assetNumber, serialNumber, manufacturer, type, model, statusId, vendorId, locationId);
    }
}
