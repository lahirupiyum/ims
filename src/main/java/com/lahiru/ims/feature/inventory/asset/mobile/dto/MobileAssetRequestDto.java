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

    public MobileAssetRequestDto(String assetNumber, String serialNumber, int quantity, int statusId, Integer vendorId, Integer locationId, ManufacturerDto manufacturer, TypeDto type, ModelDto model, int warrantyExpireDate, int purchaseDate, String invoiceNumber, EmployeeDto employee) {
        super(assetNumber, serialNumber, quantity, statusId, vendorId, locationId, manufacturer, type, model);
        this.warrantyExpireDate = warrantyExpireDate;
        this.purchaseDate = purchaseDate;
        this.invoiceNumber = invoiceNumber;
        this.employee = employee;
    }
}
