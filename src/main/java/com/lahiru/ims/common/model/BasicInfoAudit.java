package com.lahiru.ims.common.model;

import com.lahiru.ims.common.enums.AssetType;
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
// For Extend To Model Type Status
public class BasicInfoAudit extends IDNameAudit {
    @Enumerated(EnumType.STRING)
    private AssetType assetType;

    public BasicInfoAudit(String name, AssetType assetType) {
        super(name);
        this.assetType = assetType;
    }
}
