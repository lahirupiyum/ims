package com.lahiru.ims.invantory.status;

import com.lahiru.ims.common.model.BasicAudit;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "status")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Status extends BasicAudit {

}
