package com.lahiru.ims.feature.inventory.asset.mobile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileRepo extends JpaRepository<Mobile, Integer> {
}
