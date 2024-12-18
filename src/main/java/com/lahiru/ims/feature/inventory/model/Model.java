package com.lahiru.ims.feature.inventory.model;


import com.lahiru.ims.common.model.BasicInfoAudit;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "model")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Getter
@Setter
public class Model extends BasicInfoAudit {
}
