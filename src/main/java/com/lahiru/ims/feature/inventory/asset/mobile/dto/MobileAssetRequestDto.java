package com.lahiru.ims.feature.inventory.asset.mobile.dto;

import com.lahiru.ims.common.dto.feature.AssetRequestDto;
import com.lahiru.ims.feature.inventory.employee.dto.EmployeeDto;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class MobileAssetRequestDto extends AssetRequestDto {
    private int warrantyExpireDate;
    private int purchaseDate;
    private String invoiceNumber;
    private EmployeeDto employee;

    public MobileAssetRequestDto(String assetNumber, String serialNumber, ManufacturerDto manufacturer, TypeDto type, ModelDto model, Integer statusId, Integer vendorId, Integer locationId, int warrantyExpireDate, int purchaseDate, String invoiceNumber, EmployeeDto employee) {
        super(assetNumber, serialNumber, manufacturer, type, model, statusId, vendorId, locationId);
        this.warrantyExpireDate = warrantyExpireDate;
        this.purchaseDate = purchaseDate;
        this.invoiceNumber = invoiceNumber;
        this.employee = employee;
    }
}
