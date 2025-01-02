package com.lahiru.ims.common.dto.feature;

import com.lahiru.ims.feature.inventory.location.dto.LocationResponseDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.vendor.dto.VendorResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssetResponseDto extends AssetDto{
    private Integer id;
    private VendorResponseDto vendor;
    private LocationResponseDto location;
    private StatusDto status;
}
