package com.lahiru.ims.feature.customer.router.firewallcredentials;

import com.lahiru.ims.common.service.ModelMapperService;
import com.lahiru.ims.feature.customer.router.firewallcredentials.dto.RouterFirewallCredentialsRequestDto;
import com.lahiru.ims.feature.customer.router.firewallcredentials.dto.RouterFirewallCredentialsResponseDto;

public interface RouterFirewallCredentialsService extends
        ModelMapperService<RouterFirewallCredentials, RouterFirewallCredentialsRequestDto, RouterFirewallCredentialsResponseDto> {
    RouterFirewallCredentials createOne(RouterFirewallCredentialsRequestDto firewallCredentialsDto) throws Exception;
    RouterFirewallCredentials updateOne(Integer id, RouterFirewallCredentialsRequestDto firewallCredentialsDto) throws Exception;

}
