package com.lahiru.ims.feature.inventory.employee;

import com.lahiru.ims.common.model.IDNameAudit;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Employee extends IDNameAudit {
    public Employee(int id, String name) {
        super(id, name);
    }
}
