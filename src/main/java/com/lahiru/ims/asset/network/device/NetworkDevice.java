package com.lahiru.ims.asset.network.device;

import com.lahiru.ims.asset.network.manufacturer.NetworkDeviceManufacturer;
import com.lahiru.ims.asset.network.model.NetworkDeviceModel;
import com.lahiru.ims.asset.network.status.NetworkDeviceStatus;
import com.lahiru.ims.asset.network.type.NetworkDeviceType;
import com.lahiru.ims.branch.Branch;
import com.lahiru.ims.vendor.Vendor;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NetworkDevice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String serialNumber;
    private Integer quantity;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private NetworkDeviceType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private NetworkDeviceManufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    private NetworkDeviceModel model;

    @ManyToOne(fetch = FetchType.LAZY)
    private NetworkDeviceStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vendor vendor;
}
