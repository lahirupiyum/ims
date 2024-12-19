package com.lahiru.ims.common.dto.feature;

import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AssetRequestDto {
    private String assetNumber;
    private String serialNumber;
    private int quantity;
    private int statusId;
    private Integer vendorId;
    private Integer locationId;

    private ManufacturerDto manufacturer;
    private TypeDto type;
    private ModelDto model;

}
