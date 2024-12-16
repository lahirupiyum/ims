package com.lahiru.ims.invantory.asset.fixed;

import com.lahiru.ims.common.model.AssetAudit;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "fixed")
@AllArgsConstructor
//@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Fixed extends AssetAudit {

}
