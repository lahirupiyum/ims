package com.lahiru.ims.feature.inventory.status;

import com.lahiru.ims.common.model.BasicInfoAudit;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "status")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Status extends BasicInfoAudit {

}
