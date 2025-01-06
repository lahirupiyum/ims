package com.lahiru.ims.feature.inventory.vendor;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;

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
    @Column(name ="active_status ",columnDefinition = "TINYINT DEFAULT 1",nullable = false)
    private boolean isActive =true;


    public Vendor(String name, String email, String contactNo) {
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
    }
}
