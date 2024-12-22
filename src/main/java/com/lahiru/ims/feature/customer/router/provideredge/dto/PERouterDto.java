package com.lahiru.ims.feature.customer.router.provideredge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class PERouterDto {
    private String name;
    private String port;
    private String ip;
    private String wanIpPool;
    private String switchPort;
}
