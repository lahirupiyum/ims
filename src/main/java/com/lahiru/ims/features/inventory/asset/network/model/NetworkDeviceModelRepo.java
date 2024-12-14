package com.lahiru.ims.features.inventory.asset.network.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NetworkDeviceModelRepo extends JpaRepository<NetworkDeviceModel, Integer>{
    Page<NetworkDeviceModel> findAllByIsActive(Boolean isActive, Pageable pageable);
    List<NetworkDeviceModel> findAllByIsActive(Boolean isActive);
    Optional<NetworkDeviceModel> findByIdAndIsActive(Integer id, Boolean isActive);
    Boolean existsByNameAndIsActive(String name, Boolean isActive);
}
