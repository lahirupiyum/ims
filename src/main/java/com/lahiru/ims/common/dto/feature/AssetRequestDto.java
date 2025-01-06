package com.lahiru.ims.common.dto.feature;

import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
@NoArgsConstructor
public class AssetRequestDto extends AssetDto {
    private Integer statusId;
    private Integer vendorId;
    private Integer locationId;

    public AssetRequestDto(String assetNumber, String serialNumber, ManufacturerDto manufacturer, TypeDto type, ModelDto model, Integer statusId, Integer vendorId, Integer locationId) {
        super(assetNumber, serialNumber, manufacturer, type, model);
        this.statusId = statusId;
        this.vendorId = vendorId;
        this.locationId = locationId;
    }
}
