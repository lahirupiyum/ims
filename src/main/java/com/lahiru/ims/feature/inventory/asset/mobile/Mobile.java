package com.lahiru.ims.feature.inventory.asset.mobile;

import com.lahiru.ims.common.model.AssetAudit;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "mobile")
@AllArgsConstructor
//@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Mobile extends AssetAudit {
}
