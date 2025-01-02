package com.lahiru.ims.common.repository;

import com.lahiru.ims.common.model.AssetAudit;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssetRepo <Model extends AssetAudit> extends StatusAwareRepo<Model> {
    @Query("SELECT e FROM #{#entityName} e WHERE e.serialNumber LIKE %:serialNumber%")
    List<Model> findBySerialNumber(String serialNumber);
}
