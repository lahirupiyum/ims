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
public class NetworkAssetRequest extends AssetRequestDto {
    public NetworkAssetRequest(String assetNumber, String serialNumber, int quantity, int statusId, Integer vendorId, Integer locationId, ManufacturerDto manufacturer, TypeDto type, ModelDto model) {
        super(assetNumber, serialNumber, quantity, statusId, vendorId, locationId, manufacturer, type, model);
    }
}
