package com.lahiru.ims.features.inventory.vendor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    @Column(nullable = false)
    private Boolean isActive;

    public Vendor (String name, String email, String contactNo) {
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.isActive = true;
    }
}
