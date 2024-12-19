package com.lahiru.ims.feature.customer.router.provideredge;

import com.lahiru.ims.feature.inventory.asset.network.Network;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PERouter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
