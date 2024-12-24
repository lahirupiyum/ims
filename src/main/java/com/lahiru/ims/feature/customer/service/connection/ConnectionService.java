package com.lahiru.ims.feature.customer.service.connection;

import com.lahiru.ims.common.service.GenericService;
import com.lahiru.ims.common.service.ModelMapperService;
import com.lahiru.ims.feature.customer.service.connection.dto.ConnectionRequestDto;
import com.lahiru.ims.feature.customer.service.connection.dto.ConnectionResponseDto;

public interface ConnectionService extends
        GenericService<ConnectionRequestDto, ConnectionResponseDto>,
        ModelMapperService<Connection, ConnectionRequestDto, ConnectionResponseDto> {
}
