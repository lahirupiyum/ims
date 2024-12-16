package com.lahiru.ims.invantory.model;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.common.model.BasicAudit;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "model")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Getter
@Setter
public class Model extends BasicAudit {
    public Model(String name, AssetType assetType, boolean active) {
        super(name, assetType, active);
    }
}
