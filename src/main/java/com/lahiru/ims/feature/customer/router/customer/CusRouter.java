package com.lahiru.ims.feature.customer.router.customer;

import com.lahiru.ims.common.model.StatusAwareAudit;
import com.lahiru.ims.feature.inventory.asset.network.NetworkAsset;
import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class CusRouter extends StatusAwareAudit {
    private Integer id;
    @Column(name = "bandwidth")
    private String bandwidthMbps;
    private String wanIpAddress;
    private String lanIpPool;
    private String asNumber;
    private RouterOwnership ownership;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "network_asset_id")
    private NetworkAsset networkAsset;
}
