package com.lahiru.ims.common.repository;

import com.lahiru.ims.common.model.AssetAudit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface AssetRepo <Model extends AssetAudit> extends StatusAwareRepo<Model> {
    @Query("SELECT e FROM #{#entityName} e WHERE e.serialNumber LIKE %:serialNumber% AND e.isActive = true")
    List<Model> findBySerialNumber(String serialNumber);

    @Query("SELECT e FROM #{#entityName} e WHERE " +
            "(e.serialNumber LIKE %:key% OR " +
            "e.assetNumber LIKE %:key% OR " +
            "e.vendor.name LIKE %:key% OR " +
            "e.location.name LIKE %:key% OR " +
            "e.manufacturer.name LIKE %:key% OR " +
            "e.type.name LIKE %:key% OR " +
            "e.model.name LIKE %:key% OR " +
            "e.status.name LIKE %:key% ) AND " +
            "e.isActive = true")
    List<Model> search(String key);
}
