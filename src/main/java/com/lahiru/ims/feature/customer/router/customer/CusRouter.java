package com.lahiru.ims.feature.customer.router.customer;

import com.lahiru.ims.common.model.StatusAwareAudit;
import com.lahiru.ims.feature.inventory.asset.network.Network;
import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class CusRouter extends StatusAwareAudit {
    private Integer id;
    private String wanPort;
    private String lanPort;
    @Column(name = "bandwidth")
    private String bandwidthMbps;
    private String wanIpPool;
    private String lanIpPool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "network_asset_id")
    private Network networkAsset;
}
