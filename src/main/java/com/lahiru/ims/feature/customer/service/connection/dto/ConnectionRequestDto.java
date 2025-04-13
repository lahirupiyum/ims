package com.lahiru.ims.feature.customer.service.connection.dto;

import com.lahiru.ims.feature.customer.lastmile.connection.dto.LastMileConnectionRequestDto;
import com.lahiru.ims.feature.customer.router.firewallcredentials.dto.RouterFirewallCredentialsRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ConnectionRequestDto extends ConnectionDto {
    private LastMileConnectionRequestDto lastMileConnection;
    private Integer customerId;
    private Integer peRouterId;
    private Integer cusRouterId;
    private RouterFirewallCredentialsRequestDto firewallCredentials;
}
