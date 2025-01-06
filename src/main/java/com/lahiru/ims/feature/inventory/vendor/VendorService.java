package com.lahiru.ims.feature.inventory.vendor;


import com.lahiru.ims.common.service.EntityFinderService;
import com.lahiru.ims.common.service.GenericBasicInfoService;
import com.lahiru.ims.common.service.GenericSearchService;
import com.lahiru.ims.common.service.GenericService;
import com.lahiru.ims.feature.inventory.vendor.dto.VendorRequestDto;
import com.lahiru.ims.feature.inventory.vendor.dto.VendorResponseDto;

public interface VendorService extends
        GenericService<VendorRequestDto, VendorResponseDto>,
        EntityFinderService<Vendor>,
        GenericSearchService<VendorResponseDto> {

}
