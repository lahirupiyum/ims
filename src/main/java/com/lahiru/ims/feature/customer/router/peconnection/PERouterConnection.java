package com.lahiru.ims.feature.customer.router.peconnection;

import com.lahiru.ims.common.model.StatusAwareAudit;
import com.lahiru.ims.feature.inventory.asset.network.NetworkAsset;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PERouterConnection extends StatusAwareAudit {
    private String peInterface;
    private String ip;
    private String wanIpPool;
    private String switchPort;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pe_router")
    private NetworkAsset peRouter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "switch")
    private NetworkAsset networkAssetSwitch;
}
