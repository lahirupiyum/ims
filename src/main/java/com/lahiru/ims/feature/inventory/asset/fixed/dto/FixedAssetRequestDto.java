package com.lahiru.ims.feature.inventory.asset.fixed.dto;

import com.lahiru.ims.common.dto.feature.AssetRequestDto;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class FixedAssetRequestDto extends AssetRequestDto {
    private String invoiceNumber;
    private String deprecationInfo;
    private Date purchaseDate;

    public FixedAssetRequestDto(String assetNumber, String serialNumber, int quantity, int statusId, Integer vendorId, Integer locationId, ManufacturerDto manufacturer, TypeDto type, ModelDto model, String invoiceNumber, String deprecationInfo, Date purchaseDate) {
        super(assetNumber, serialNumber, quantity, statusId, vendorId, locationId, manufacturer, type, model);
        this.invoiceNumber = invoiceNumber;
        this.deprecationInfo = deprecationInfo;
        this.purchaseDate = purchaseDate;
    }
}
