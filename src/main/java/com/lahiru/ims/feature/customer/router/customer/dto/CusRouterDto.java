package com.lahiru.ims.feature.customer.router.customer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CusRouterDto {
    private String wanPort;
    private String lanPort;
    private String bandwidth;
    private String wanIpPool;
    private String lanIpPool;
}
