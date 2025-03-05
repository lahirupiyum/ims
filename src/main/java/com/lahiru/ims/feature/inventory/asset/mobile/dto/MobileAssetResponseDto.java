package com.lahiru.ims.feature.inventory.asset.mobile.dto;

import com.lahiru.ims.common.dto.feature.AssetResponseDto;
import com.lahiru.ims.feature.inventory.employee.dto.EmployeeDto;
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

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class MobileAssetResponseDto extends AssetResponseDto {
    private Date warrantyExpireDate;
    private Date purchaseDate;
    private String invoiceNumber;
    private EmployeeDto employee;

    public MobileAssetResponseDto(String assetNumber, String serialNumber, ManufacturerDto manufacturer, TypeDto type, ModelDto model, Integer id, VendorResponseDto vendor, LocationResponseDto location, StatusDto status, Date warrantyExpireDate, Date purchaseDate, String invoiceNumber, EmployeeDto employee) {
        super(assetNumber, serialNumber, manufacturer, type, model, id, vendor, location, status);
        this.warrantyExpireDate = warrantyExpireDate;
        this.purchaseDate = purchaseDate;
        this.invoiceNumber = invoiceNumber;
        this.employee = employee;
    }
}
