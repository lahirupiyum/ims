package com.lahiru.ims.common.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
@EnableJpaAuditing
public class StatusAwareAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean isActive;
}
