package com.lahiru.ims.feature.inventory.asset.fixed;

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
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "fixed")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Fixed extends AssetAudit {
    private String invoiceNumber;
    private String deprecationInfo;
    private Date purchaseDate;

    public Fixed(String assetNumber, String serialNumber, int quantity, Vendor vendor, Location location, Model model, Type type, Status status, Manufacturer manufacturer, String invoiceNumber, String deprecationInfo, Date purchaseDate) {
        super(assetNumber, serialNumber, quantity, vendor, location, model, type, status, manufacturer);
        this.invoiceNumber = invoiceNumber;
        this.deprecationInfo = deprecationInfo;
        this.purchaseDate = purchaseDate;
    }
}
