package com.lahiru.ims.feature.inventory.asset.mobile;

import com.lahiru.ims.common.model.AssetAudit;
import com.lahiru.ims.feature.inventory.employee.Employee;
import com.lahiru.ims.feature.inventory.location.Location;
import com.lahiru.ims.feature.inventory.manufacturer.Manufacturer;
import com.lahiru.ims.feature.inventory.model.Model;
import com.lahiru.ims.feature.inventory.status.Status;
import com.lahiru.ims.feature.inventory.type.Type;
import com.lahiru.ims.feature.inventory.vendor.Vendor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "mobile")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Mobile extends AssetAudit {
    private Date warrantyExpireDate;
    private Date purchaseDate;
    private String invoiceNumber;
    @OneToOne()
    @JoinColumn(name = "employee")
    private Employee employee;

    public Mobile(String assetNumber, String serialNumber, int quantity, Vendor vendor, Location location, Model model, Type type, Status status, Manufacturer manufacturer, Date warrantyExpireDate, Date purchaseDate, String invoiceNumber, Employee employee) {
        super(assetNumber, serialNumber, vendor, location, model, type, status, manufacturer);
        this.warrantyExpireDate = warrantyExpireDate;
        this.purchaseDate = purchaseDate;
        this.invoiceNumber = invoiceNumber;
        this.employee = employee;
    }
}
