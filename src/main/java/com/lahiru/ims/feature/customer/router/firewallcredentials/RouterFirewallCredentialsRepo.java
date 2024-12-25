package com.lahiru.ims.feature.customer.router.firewallcredentials;

import com.lahiru.ims.common.repository.StatusAwareRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface RouterFirewallCredentialsRepo extends StatusAwareRepo<RouterFirewallCredentials> {
}
