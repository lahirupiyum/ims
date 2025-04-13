package com.lahiru.ims.feature.customer.router.customer;

import com.lahiru.ims.common.service.EntityFinderService;
import com.lahiru.ims.common.service.GenericService;
import com.lahiru.ims.common.service.ModelMapperService;
import com.lahiru.ims.feature.customer.router.customer.dto.CusRouterRequestDto;
import com.lahiru.ims.feature.customer.router.customer.dto.CusRouterResponseDto;
import com.lahiru.ims.feature.inventory.status.enums.NetworkAssetStatus;

public interface CusRouterService extends
        GenericService<CusRouterRequestDto, CusRouterResponseDto>,
        ModelMapperService<CusRouter, CusRouterRequestDto, CusRouterResponseDto>,
        EntityFinderService<CusRouter> {
}
