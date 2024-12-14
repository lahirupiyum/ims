package com.lahiru.ims.features.inventory.asset.network.manufacturer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NetworkDeviceManufacturerRepo extends JpaRepository<NetworkDeviceManufacturer, Integer> {
    @Query("SELECT m FROM NetworkDeviceManufacturer m WHERE m.isActive = TRUE")
    Page<NetworkDeviceManufacturer> findAllActive(Pageable pageable);

    @Query("SELECT m FROM NetworkDeviceManufacturer m WHERE m.isActive = TRUE")
    List<NetworkDeviceManufacturer> findAllActive();

    Boolean existsByNameAndIsActive(String name, Boolean isActive);

    @Query("SELECT m FROM NetworkDeviceManufacturer m WHERE m.id = :id AND m.isActive = TRUE")
    Optional<NetworkDeviceManufacturer> findActiveById(Integer id);
}
