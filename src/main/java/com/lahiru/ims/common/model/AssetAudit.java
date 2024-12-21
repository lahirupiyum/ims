package com.lahiru.ims.common.model;


import com.lahiru.ims.feature.inventory.location.Location;
import com.lahiru.ims.feature.inventory.manufacturer.Manufacturer;
import com.lahiru.ims.feature.inventory.model.Model;
import com.lahiru.ims.feature.inventory.status.Status;
import com.lahiru.ims.feature.inventory.type.Type;
import com.lahiru.ims.feature.inventory.vendor.Vendor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
@EnableJpaAuditing
// to extend All asset To Needed Common Value
public class AssetAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String assetNumber;
    private String serialNumber;
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufaturer_id")
    private Manufacturer manufacturer;

    public AssetAudit(String assetNumber, String serialNumber, int quantity, Vendor vendor, Location location, Model model, Type type, Status status, Manufacturer manufacturer) {
        this.assetNumber = assetNumber;
        this.serialNumber = serialNumber;
        this.quantity = quantity;
        this.vendor = vendor;
        this.location = location;
        this.model = model;
        this.type = type;
        this.status = status;
        this.manufacturer = manufacturer;
    }
}
