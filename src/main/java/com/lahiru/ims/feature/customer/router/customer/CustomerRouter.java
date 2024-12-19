package com.lahiru.ims.feature.customer.router.customer;

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
public class CustomerRouter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String wanPort;
    private String lanPort;
    @Column(name = "bandwidth")
    private String bandwidthMbps;
    private String wanIpPool;
    private String lanIpPool;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "network_asset_id")
    private Network networkAsset;
}
