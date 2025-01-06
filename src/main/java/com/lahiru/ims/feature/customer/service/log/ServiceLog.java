package com.lahiru.ims.feature.customer.service.log;

import com.lahiru.ims.feature.customer.service.connection.Connection;
import com.lahiru.ims.feature.customer.service.enums.ServiceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private ServiceType serviceType;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private Connection service;

}
