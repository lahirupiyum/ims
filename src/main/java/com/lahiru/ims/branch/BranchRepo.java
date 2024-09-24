package com.lahiru.ims.branch;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepo extends JpaRepository<Branch, Integer>{
    @Query("SELECT b FROM Branch b WHERE b.status = true")
    Page<Branch> findAllActive(org.springframework.data.domain.Pageable pageable);
    @Query("SELECT b FROM Branch b WHERE b.status = true")
    List<Branch> findAllActive();
    
    @Query("SELECT b FROM Branch b WHERE b.id = :id AND b.status = true")
    Optional<Branch> findActiveById(Integer id);
    Boolean existsByIdAndStatus(Integer id, Boolean status);
}
