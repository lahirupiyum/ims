package com.lahiru.ims.feature.customer.router.peconnection;

import com.lahiru.ims.common.service.EntityFinderService;
import com.lahiru.ims.common.service.GenericService;
import com.lahiru.ims.common.service.ModelMapperService;
import com.lahiru.ims.feature.customer.router.peconnection.dto.PERouterConnectionRequestDto;
import com.lahiru.ims.feature.customer.router.peconnection.dto.PERouterConnectionResponseDto;

public interface PERouterConnectionService extends
        GenericService<PERouterConnectionRequestDto, PERouterConnectionResponseDto>,
        ModelMapperService<PERouterConnection, PERouterConnectionRequestDto, PERouterConnectionResponseDto>,
        EntityFinderService<PERouterConnection> {
}
