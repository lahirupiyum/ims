package com.lahiru.ims.feature.customer.service.connection;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.service.GenericService;
import com.lahiru.ims.common.service.ModelMapperService;
import com.lahiru.ims.feature.customer.router.firewallcredentials.RouterFirewallCredentials;
import com.lahiru.ims.feature.customer.service.connection.dto.ConnectionRequestDto;
import com.lahiru.ims.feature.customer.service.connection.dto.ConnectionResponseDto;

public interface ConnectionService extends
        GenericService<ConnectionRequestDto, ConnectionResponseDto>,
        ModelMapperService<Connection, ConnectionRequestDto, ConnectionResponseDto> {

    PaginationResponse<ConnectionResponseDto> findIllByPageWise(int page, int pageSize) throws Exception;
    PaginationResponse<ConnectionResponseDto> findMplsByPageWise(int page, int pageSize) throws Exception;

    ConnectionResponseDto activateConnection(Integer id) throws Exception;
}
