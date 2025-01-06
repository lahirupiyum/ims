package com.lahiru.ims.feature.customer.router.firewallcredentials;

import com.lahiru.ims.common.service.ModelMapperService;
import com.lahiru.ims.feature.customer.router.firewallcredentials.dto.RouterFirewallCredentialsDto;

public interface RouterFirewallCredentialsService extends
        ModelMapperService<RouterFirewallCredentials, RouterFirewallCredentialsDto, RouterFirewallCredentialsDto> {
    RouterFirewallCredentials createOne(RouterFirewallCredentialsDto firewallCredentialsDto) throws Exception;
    RouterFirewallCredentials updateOne(Integer id, RouterFirewallCredentialsDto firewallCredentialsDto) throws Exception;

}
