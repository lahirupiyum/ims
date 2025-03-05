package com.lahiru.ims.feature.customer.router.firewallcredentials;

import com.lahiru.ims.common.model.StatusAwareAudit;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RouterFirewallCredentials extends StatusAwareAudit {
    private String username;
    private String password;

    public RouterFirewallCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public RouterFirewallCredentials(Integer id, String username, String password) {
        super(id, true);
        this.username = username;
        this.password = password;
    }
}
