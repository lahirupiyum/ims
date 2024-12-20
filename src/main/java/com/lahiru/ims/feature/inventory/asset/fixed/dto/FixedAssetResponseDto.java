package com.lahiru.ims.feature.inventory.asset.fixed.dto;

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

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class FixedAssetResponseDto extends AssetResponseDto {
    private String invoiceNumber;
    private String deprecationInfo;
    private Date purchaseDate;

    public FixedAssetResponseDto(Integer id, ManufacturerDto manufacturer, String deprecationInfo, String assetNumber, String serialNumber, VendorResponseDto vendor, LocationResponseDto location, int quantity, ModelDto model, TypeDto type, StatusDto status, String invoiceNumber, String deprecationInfo1, Date purchaseDate) {
        super(id, manufacturer, deprecationInfo, assetNumber, serialNumber, vendor, location, quantity, model, type, status);
        this.invoiceNumber = invoiceNumber;
        this.deprecationInfo = deprecationInfo1;
        this.purchaseDate = purchaseDate;
    }
}
