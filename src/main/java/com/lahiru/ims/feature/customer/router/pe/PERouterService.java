package com.lahiru.ims.feature.customer.router.pe;

import com.lahiru.ims.common.service.EntityFinderService;
import com.lahiru.ims.common.service.GenericService;
import com.lahiru.ims.common.service.ModelMapperService;
import com.lahiru.ims.feature.customer.router.pe.dto.PERouterRequestDto;
import com.lahiru.ims.feature.customer.router.pe.dto.PERouterResponseDto;

public interface PERouterService extends
        GenericService<PERouterRequestDto, PERouterResponseDto>,
        ModelMapperService<PERouter, PERouterRequestDto, PERouterResponseDto>,
        EntityFinderService<PERouter> {
}
