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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
@EnableJpaAuditing
// to extend All asset To Needed Common Value
public class AssetAudit extends StatusAwareAudit {
    private String assetNumber;
    private String serialNumber;
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

}
