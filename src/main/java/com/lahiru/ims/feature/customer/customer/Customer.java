package com.lahiru.ims.feature.customer.customer;

import com.lahiru.ims.common.model.StatusAwareAudit;
import com.lahiru.ims.feature.inventory.employee.Employee;
import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Customer extends StatusAwareAudit {
    private String name;
    private String address;
    private String contactNo;
    private String email;
    private String vsnlId;
    @Enumerated(EnumType.STRING)
    private CustomerPriority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_manager")
    private Employee accountManager;
}
