package com.lahiru.ims.features.inventory.asset.network.type;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NetworkDeviceTypeRepo extends JpaRepository<NetworkDeviceType, Integer> {
    List<NetworkDeviceType> findAllByIsActive(Boolean isActive);
    Page<NetworkDeviceType> findAllByIsActive(Boolean isActive, Pageable pageable);

    Optional<NetworkDeviceType> findByIdAndIsActive(Integer id, Boolean isActive);

    Boolean existsByNameAndIsActive(String name, Boolean isActive);
}