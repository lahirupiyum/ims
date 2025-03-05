package com.lahiru.ims.feature.customer.router.firewallcredentials.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RouterFirewallCredentialsResponseDto {
    private Integer id;
    private String username;
    private String password;
}
