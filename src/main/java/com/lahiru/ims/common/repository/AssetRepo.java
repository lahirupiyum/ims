package com.lahiru.ims.common.repository;

import com.lahiru.ims.common.model.AssetAudit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface AssetRepo <Model extends AssetAudit> extends StatusAwareRepo<Model> {
    @Query("SELECT e FROM #{#entityName} e WHERE e.serialNumber LIKE %:serialNumber% AND e.isActive = true")
    List<Model> findBySerialNumber(String serialNumber);
}
