package com.lahiru.ims.common.model;

import com.lahiru.ims.invantory.vendor.Vendor;
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
// to extend All asset To Needed Common Value
public class AssetAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int assetNumber;
//    @ManyToOne
//    @JoinColumn(name = "manufacture")
//   private Manufacture manufacture;
    @ManyToOne
    @JoinColumn(name = "vendor")
    private Vendor vendor;
//    @ManyToOne
//    @JoinColumn(name = "model")
//    private Model model;

    private int type;
    private int status;
    private int serialNumber;


}
