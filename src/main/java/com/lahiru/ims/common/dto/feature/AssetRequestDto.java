package com.lahiru.ims.common.dto.feature;

import com.lahiru.ims.feature.inventory.manufacturer.Manufacturer;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerRequestDto;
import lombok.Data;

@Data
public class AssetRequestDto {
    private ManufacturerRequestDto manufacturer;
}
