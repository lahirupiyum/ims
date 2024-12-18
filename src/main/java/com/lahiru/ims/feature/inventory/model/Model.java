package com.lahiru.ims.feature.inventory.model;


import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.common.model.BasicInfoAudit;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "model")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Model extends BasicInfoAudit {
    public Model(String name, AssetType assetType) {
        super(name, assetType);
    }
}
