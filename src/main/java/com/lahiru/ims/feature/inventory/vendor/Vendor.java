package com.lahiru.ims.feature.inventory.vendor;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Vendor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String contactNo;
    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean isActive;

    public Vendor(String name, String email, String contactNo) {
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
    }
}
