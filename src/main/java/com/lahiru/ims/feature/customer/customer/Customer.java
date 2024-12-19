package com.lahiru.ims.feature.customer.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    private String contactNo;
    private String email;
    private String vsnlId;
    private String asNumber;
    private CustomerPriority priority;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "account_manager")
//    private Employee accountManager;
}
