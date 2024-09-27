package com.lahiru.ims.asset.network.status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<NetworkDeviceStatus, Integer> {
    
}
