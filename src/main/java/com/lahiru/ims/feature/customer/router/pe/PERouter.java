package com.lahiru.ims.feature.customer.router.pe;

import com.lahiru.ims.common.model.IDNameAudit;
import com.lahiru.ims.feature.inventory.asset.network.Network;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class PERouter extends IDNameAudit {
    @OneToOne(fetch = FetchType.LAZY)
    private Network asset;
}
