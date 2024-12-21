package com.lahiru.ims.feature.inventory.asset.network;

import com.lahiru.ims.common.model.AssetAudit;
import com.lahiru.ims.feature.inventory.location.Location;
import com.lahiru.ims.feature.inventory.manufacturer.Manufacturer;
import com.lahiru.ims.feature.inventory.model.Model;
import com.lahiru.ims.feature.inventory.status.Status;
import com.lahiru.ims.feature.inventory.type.Type;
import com.lahiru.ims.feature.inventory.vendor.Vendor;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "network")
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Network extends AssetAudit {
    public Network(String assetNumber, String serialNumber, int quantity, Vendor vendor, Location location, Model model, Type type, Status status, Manufacturer manufacturer) {
        super(assetNumber, serialNumber, quantity, vendor, location, model, type, status, manufacturer);
    }
}
