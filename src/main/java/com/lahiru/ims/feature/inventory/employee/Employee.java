package com.lahiru.ims.feature.inventory.employee;

import com.lahiru.ims.common.model.BasicCustomerAudit;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Employee extends BasicCustomerAudit {
    public Employee(int id, String name) {
        super(id, name);
    }
}
