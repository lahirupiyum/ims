package com.lahiru.ims.feature.customer.router.peconnection;

import com.lahiru.ims.common.model.StatusAwareAudit;
import com.lahiru.ims.feature.customer.router.pe.PERouter;
import com.lahiru.ims.feature.inventory.asset.network.Network;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PERouterConnection extends StatusAwareAudit {
    private String port;
    private String ip;
    private String wanIpPool;
    private String switchPort;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pe_router")
    private PERouter peRouter;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "switch")
    private Network networkSwitch;
}
