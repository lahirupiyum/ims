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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class NetworkDevice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String serialNumber;
    private Integer quantity;
    private Boolean isActive;
    
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

    public NetworkDevice(String serialNumber, 
                        Integer quantity, 
                        NetworkDeviceType type, 
                        NetworkDeviceManufacturer manufacturer, 
                        NetworkDeviceModel model,
                        NetworkDeviceStatus status,
                        Branch branch,
                        Vendor vendor) {
        this.serialNumber = serialNumber;
        this.quantity = quantity;
        this.type = type;
        this.manufacturer = manufacturer;
        this.model = model;
        this.status = status;
        this.branch = branch;
        this.vendor = vendor;
        this.isActive = true;
    }
}
