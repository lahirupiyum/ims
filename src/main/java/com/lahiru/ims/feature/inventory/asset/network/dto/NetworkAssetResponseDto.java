package com.lahiru.ims.feature.inventory.asset.network.dto;

import com.lahiru.ims.common.dto.feature.AssetResponseDto;
import com.lahiru.ims.feature.inventory.location.dto.LocationResponseDto;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import com.lahiru.ims.feature.inventory.vendor.dto.VendorResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class NetworkAssetResponseDto extends AssetResponseDto {
    public NetworkAssetResponseDto(String assetNumber, String serialNumber, ManufacturerDto manufacturer, TypeDto type, ModelDto model, Integer id, VendorResponseDto vendor, LocationResponseDto location, StatusDto status) {
        super(assetNumber, serialNumber, manufacturer, type, model, id, vendor, location, status);
    }
}
