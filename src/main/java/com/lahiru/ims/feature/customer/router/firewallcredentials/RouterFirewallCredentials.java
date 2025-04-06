package com.lahiru.ims.feature.customer.router.firewallcredentials;

import com.lahiru.ims.common.model.StatusAwareAudit;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RouterFirewallCredentials extends StatusAwareAudit {
    private String ip;
    private String port;

    public RouterFirewallCredentials(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public RouterFirewallCredentials(Integer id, String ip, String port) {
        super(id, true);
        this.ip = ip;
        this.port = port;
    }
}
