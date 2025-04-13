package com.lahiru.ims.feature.customer.router.peconnection.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class PERouterConnectionDto {
    private String peInterface;
    private String ip;
    private String wanIpPool;
    private String switchPort;
}
