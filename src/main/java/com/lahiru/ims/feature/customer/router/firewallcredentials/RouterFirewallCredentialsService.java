package com.lahiru.ims.feature.customer.router.firewallcredentials;

import com.lahiru.ims.feature.customer.router.firewallcredentials.dto.RouterFirewallCredentialsDto;

public interface RouterFirewallCredentialsService {
    RouterFirewallCredentialsDto createOne(RouterFirewallCredentialsDto firewallCredentialsDto) throws Exception;
    RouterFirewallCredentialsDto updateOne(Integer id, RouterFirewallCredentialsDto firewallCredentialsDto) throws Exception;
    RouterFirewallCredentialsDto getOne(Integer id) throws Exception;
}
