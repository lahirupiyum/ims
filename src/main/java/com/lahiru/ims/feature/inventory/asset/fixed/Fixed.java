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

@Entity
@Table(name = "fixed")
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Fixed extends AssetAudit {

}
