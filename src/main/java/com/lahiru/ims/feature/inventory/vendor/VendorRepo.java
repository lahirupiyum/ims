package com.lahiru.ims.feature.inventory.vendor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface VendorRepo extends JpaRepository<Vendor, Integer> {
    Page<Vendor> findAllByIsActive(Pageable pageable, Boolean status);
    List<Vendor> findAllByIsActive(Boolean status);

    Optional<Vendor> findByIdAndIsActive(Integer id, Boolean status);
    Boolean existsByEmailAndIsActive(String email, Boolean isActive);

    List<Vendor> findAllByNameContaining(String name);
}
