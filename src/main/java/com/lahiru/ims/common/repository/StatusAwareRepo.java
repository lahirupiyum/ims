package com.lahiru.ims.common.repository;

import com.lahiru.ims.common.model.StatusAwareAudit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface StatusAwareRepo<Model extends StatusAwareAudit> extends JpaRepository<Model, Integer> {
    @Query("SELECT m FROM #{#entityName} m WHERE m.isActive = true")
    Page<Model> findAllByPageWise(Pageable pageable);

    @Query("SELECT COUNT(m) > 0 FROM #{#entityName} m WHERE m.isActive = true AND m.id = :id")
    Boolean isActiveById(Integer id);

    @Query("SELECT m FROM #{#entityName} m WHERE m.isActive = true AND m.id = :id")
    Optional<Model> findActiveOne(Integer id);

    @Query("SELECT m FROM #{#entityName} m WHERE m.isActive = true")
    List<Model> findAllActive();
}
