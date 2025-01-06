package com.lahiru.ims.common.dto.feature;

import com.lahiru.ims.feature.inventory.location.dto.LocationResponseDto;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import com.lahiru.ims.feature.inventory.vendor.dto.VendorResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssetResponseDto extends AssetDto{
    private Integer id;
    private VendorResponseDto vendor;
    private LocationResponseDto location;
    private StatusDto status;

    public AssetResponseDto(String assetNumber, String serialNumber, ManufacturerDto manufacturer, TypeDto type, ModelDto model, Integer id, VendorResponseDto vendor, LocationResponseDto location, StatusDto status) {
        super(assetNumber, serialNumber, manufacturer, type, model);
        this.id = id;
        this.vendor = vendor;
        this.location = location;
        this.status = status;
    }
}
