package com.lahiru.ims.common.dto.feature;

import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetDto {
    private String assetNumber;
    private String serialNumber;
    private ManufacturerDto manufacturer;
    private TypeDto type;
    private ModelDto model;
}
