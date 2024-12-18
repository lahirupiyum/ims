package com.lahiru.ims.common.dto.feature;

import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerRequestDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssetResponseDto {
    private Integer id;
    private String assetNumber;
    private String serialNumber;
    private Integer vendorId;
    private Integer location;
    private int quantity;
    private ModelDto model;
    private TypeDto type;
    private StatusDto status;
    private ManufacturerRequestDto manufacturer;
}
