package com.lahiru.ims.feature.inventory.manufacturer;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.common.model.BasicInfoAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "manufature")
public class Manufacturer extends BasicInfoAudit {
    public Manufacturer(String name, AssetType assetType) {
        super(name, assetType);
    }
}
