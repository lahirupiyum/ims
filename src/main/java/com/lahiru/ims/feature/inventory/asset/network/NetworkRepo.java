package com.lahiru.ims.feature.inventory.asset.network;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkRepo extends JpaRepository<Network, Integer> {
}
