package com.lahiru.ims.feature.inventory.asset.network.dto;

import com.lahiru.ims.common.dto.feature.AssetResponseDto;
import com.lahiru.ims.feature.inventory.location.dto.LocationResponseDto;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import com.lahiru.ims.feature.inventory.vendor.dto.VendorResponseDto;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class NetworkAssetResponseDto extends AssetResponseDto {
    public NetworkAssetResponseDto(Integer id, ManufacturerDto manufacturer, String deprecationInfo, String assetNumber, String serialNumber, VendorResponseDto vendor, LocationResponseDto location, int quantity, ModelDto model, TypeDto type, StatusDto status) {
        super(id, manufacturer, deprecationInfo, assetNumber, serialNumber, vendor, location, quantity, model, type, status);
    }
}
