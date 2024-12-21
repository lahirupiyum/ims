package com.lahiru.ims.common.dto.feature;

import com.lahiru.ims.feature.inventory.location.Location;
import com.lahiru.ims.feature.inventory.location.dto.LocationResponseDto;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import com.lahiru.ims.feature.inventory.vendor.Vendor;
import com.lahiru.ims.feature.inventory.vendor.dto.VendorResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssetResponseDto {
    private Integer id;
    private ManufacturerDto manufacturer;
    private String deprecationInfo;
    private String assetNumber;
    private String serialNumber;
    private VendorResponseDto vendor;
    private LocationResponseDto location;
    private int quantity;
    private ModelDto model;
    private TypeDto type;
    private StatusDto status;
}
