package com.lahiru.ims.feature.customer.lastmile.media;

import com.lahiru.ims.common.model.BasicCustomerAudit;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class LastMileMedia extends BasicCustomerAudit {
    public LastMileMedia(Integer id, String name) {
        super(id, name);
    }
}
