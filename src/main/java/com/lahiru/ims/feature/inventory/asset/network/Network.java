package com.lahiru.ims.feature.inventory.asset.network;

import com.lahiru.ims.common.model.AssetAudit;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "network")
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Network extends AssetAudit {
}
