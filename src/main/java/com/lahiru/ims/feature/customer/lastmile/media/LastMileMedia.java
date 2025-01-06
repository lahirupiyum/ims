package com.lahiru.ims.feature.customer.lastmile.media;

import com.lahiru.ims.common.model.IDNameAudit;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class LastMileMedia extends IDNameAudit {
    public LastMileMedia(String name) {
        super(name);
    }
}
