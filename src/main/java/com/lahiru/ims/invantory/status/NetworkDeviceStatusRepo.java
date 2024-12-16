package com.lahiru.ims.invantory.status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NetworkDeviceStatusRepo extends JpaRepository<Status, Integer> {
    
}
