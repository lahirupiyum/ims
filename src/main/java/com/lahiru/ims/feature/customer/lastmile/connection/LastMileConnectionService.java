package com.lahiru.ims.feature.customer.lastmile.connection;

import com.lahiru.ims.common.service.EntityFinderService;
import com.lahiru.ims.common.service.ModelMapperService;
import com.lahiru.ims.feature.customer.lastmile.connection.dto.LastMileConnectionRequestDto;
import com.lahiru.ims.feature.customer.lastmile.connection.dto.LastMileConnectionResponseDto;

public interface LastMileConnectionService extends
        ModelMapperService<LastMileConnection, LastMileConnectionRequestDto, LastMileConnectionResponseDto>{
    LastMileConnection createOne(LastMileConnectionRequestDto requestDto) throws Exception;
    LastMileConnection updateOne(Integer id, LastMileConnectionRequestDto requestDto) throws Exception;
}
