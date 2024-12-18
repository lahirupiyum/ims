package com.lahiru.ims.feature.inventory.type;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.common.model.BasicInfoAudit;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "type")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Type extends BasicInfoAudit {
    public Type(String name, AssetType assetType) {
        super(name, assetType);
    }
}
