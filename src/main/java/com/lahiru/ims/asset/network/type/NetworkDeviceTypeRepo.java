package com.lahiru.ims.asset.network.type;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NetworkDeviceTypeRepo extends JpaRepository<NetworkDeviceType, Integer> {
    List<NetworkDeviceType> findAllByIsActive(Boolean isActive);

    Optional<NetworkDeviceType> findByIdAndIsActive(Integer id, Boolean isActive);

    Boolean existsByNameAndIsActive(String name, Boolean isActive);
}