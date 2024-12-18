package com.lahiru.ims.common.dto.feature;

import com.lahiru.ims.feature.inventory.location.Location;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import com.lahiru.ims.feature.inventory.vendor.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AssetRequestDto {
    private ManufacturerDto manufacturer;
    private String deprecationInfo;
    private String assetNumber;
    private String serialNumber;
    private Vendor vendor;
    private Location location;
    private int quantity;
    private ModelDto model;
    private TypeDto type;
    private StatusDto status;

}
