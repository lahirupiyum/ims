package com.lahiru.ims.feature.inventory.manufacturer;

import com.lahiru.ims.common.model.BasicAudit;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "manufature")
public class Manufacturer extends BasicAudit {
    private String address;
    private long contact;
    private boolean active;
}
