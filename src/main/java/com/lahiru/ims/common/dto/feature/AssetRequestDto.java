package com.lahiru.ims.common.dto.feature;

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

}
