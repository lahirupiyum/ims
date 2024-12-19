package com.lahiru.ims.feature.customer.lastmileprovider.provider;

import com.lahiru.ims.common.model.BasicCustomerAudit;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class LastMileProvider extends BasicCustomerAudit {
    public LastMileProvider(Integer id, String name) {
        super(id, name);
    }
}
