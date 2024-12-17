package com.lahiru.ims.feature.inventory.manufacturer;

import com.lahiru.ims.common.model.BasicInfoAudit;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "manufature")
public class Manufacturer extends BasicInfoAudit {
    private String address;
    private long contact;
    private boolean active;
}
