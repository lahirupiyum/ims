package com.lahiru.ims.features.inventory.location;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer>{
    @Query("SELECT b FROM Location b WHERE b.status = true")
    Page<Location> findAllActive(org.springframework.data.domain.Pageable pageable);
    @Query("SELECT b FROM Location b WHERE b.status = true")
    List<Location> findAllActive();
    
    @Query("SELECT b FROM Location b WHERE b.id = :id AND b.status = true")
    Optional<Location> findActiveById(Integer id);
    Boolean existsByIdAndStatus(Integer id, Boolean status);
}
