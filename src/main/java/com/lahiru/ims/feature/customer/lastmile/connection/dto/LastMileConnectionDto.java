package com.lahiru.ims.feature.customer.lastmile.connection.dto;

import com.lahiru.ims.feature.customer.lastmile.media.dto.LastMileMediaDto;
import com.lahiru.ims.feature.customer.lastmile.provider.dto.LastMileProviderDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class LastMileConnectionDto {
    private String switchPort;
    private String circuitId;
    private String bandwidth;
    private LastMileProviderDto lastMileProvider;
    private LastMileMediaDto media;
}
