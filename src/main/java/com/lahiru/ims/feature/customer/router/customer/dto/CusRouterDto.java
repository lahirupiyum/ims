package com.lahiru.ims.feature.customer.router.customer.dto;

import com.lahiru.ims.feature.customer.router.customer.RouterOwnership;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CusRouterDto {
    private String bandwidth;
    private String wanIpAddress;
    private String lanIpPool;
    private String asNumber;
    private RouterOwnership ownership;
}
