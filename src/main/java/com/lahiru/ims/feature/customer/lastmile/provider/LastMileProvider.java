package com.lahiru.ims.feature.customer.lastmile.provider;

import com.lahiru.ims.common.model.IDNameAudit;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class LastMileProvider extends IDNameAudit {
    public LastMileProvider(String name) {
        super(name);
    }
}
