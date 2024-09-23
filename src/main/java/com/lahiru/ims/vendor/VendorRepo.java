package com.lahiru.ims.vendor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface VendorRepo extends JpaRepository<Vendor, Integer> {
    Page<Vendor> findByStatus(Pageable pageable, Boolean status);
    List<Vendor> findByStatus(Boolean status);

    Optional<Vendor> findByIdAndStatus(Integer id, Boolean status);
    Boolean existsByIdAndStatus(Integer id, Boolean status);
}
