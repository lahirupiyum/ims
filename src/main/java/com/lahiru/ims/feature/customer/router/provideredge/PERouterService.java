package com.lahiru.ims.feature.customer.router.provideredge;

import com.lahiru.ims.common.service.GenericService;
import com.lahiru.ims.common.service.ModelMapperService;
import com.lahiru.ims.feature.customer.router.provideredge.dto.PERouterRequestDto;
import com.lahiru.ims.feature.customer.router.provideredge.dto.PERouterResponseDto;

public interface PERouterService extends
        GenericService<PERouterRequestDto, PERouterResponseDto>,
        ModelMapperService<PERouter, PERouterRequestDto, PERouterResponseDto> {
}
