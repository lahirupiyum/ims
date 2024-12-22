package com.lahiru.ims.feature.customer.router.provideredge;

import com.lahiru.ims.common.model.StatusAwareAudit;
import com.lahiru.ims.feature.inventory.asset.network.Network;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PERouter extends StatusAwareAudit {
    private String name;
    private String port;
    private String ip;
    private String wanIpPool;
    private String switchPort;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "network_asset_id")
    private Network networkAsset;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "switch")
    private Network networkSwitch;
}
