package com.lahiru.ims.feature.customer.service.connection;

import com.lahiru.ims.feature.customer.customer.Customer;
import com.lahiru.ims.feature.customer.lastmile.connection.LastMileConnection;
import com.lahiru.ims.feature.customer.router.customer.CusRouter;
import com.lahiru.ims.feature.customer.router.firewallcredentials.RouterFirewallCredentials;
import com.lahiru.ims.feature.customer.router.provideredge.PERouter;
import com.lahiru.ims.feature.customer.service.enums.ManageStatus;
import com.lahiru.ims.feature.customer.service.enums.NetworkServiceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date dsp;
    private Date serviceChange;
    private Date terminationDate;
    private Boolean activeStatus;
    private String remarks;

    private NetworkServiceType serviceType;
    private ManageStatus manageStatus;

    @OneToOne(fetch = FetchType.LAZY)
    private LastMileConnection lastMileConnection;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    private PERouter peRouter;
    @ManyToOne(fetch = FetchType.LAZY)
    private CusRouter cusRouter;
    @OneToOne(fetch = FetchType.LAZY)
    private RouterFirewallCredentials routerFirewallCredentials;

}
