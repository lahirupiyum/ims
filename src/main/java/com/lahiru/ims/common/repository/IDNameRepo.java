package com.lahiru.ims.common.repository;

import com.lahiru.ims.common.model.IDNameAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface IDNameRepo <Model extends IDNameAudit> extends JpaRepository<Model, Integer> {
    @Query("SELECT m FROM #{#entityName} m WHERE m.name LIKE %:name%")
    List<Model> searchByName(String name);
}
