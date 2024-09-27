package com.lahiru.ims.asset.network.status;

import java.util.List;

public interface StatusService {
    List<StatusResponseDto> findAll() throws Exception;
}
