package com.lahiru.ims.asset.network.manufacturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkDeviceManufacturerRepo extends JpaRepository<NetworkDeviceManufacturer, Integer> {
    
}
