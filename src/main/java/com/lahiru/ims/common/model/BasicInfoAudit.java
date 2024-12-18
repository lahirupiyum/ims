package com.lahiru.ims.common.model;

import com.lahiru.ims.common.enums.AssetType;
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
// For Extend To Model Type Status
public class BasicInfoAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private AssetType assetType;

    public BasicInfoAudit(String name, AssetType assetType) {
        this.name = name;
        this.assetType = assetType;
    }
}
