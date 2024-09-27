package com.lahiru.ims.asset.network.device;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NetworkDeviceRepo extends JpaRepository<NetworkDevice, Integer> {
    Page<NetworkDevice> findAllByIsActiveIsTrue(Pageable pageable);
    List<NetworkDevice> findAllByIsActiveIsTrue();
    Optional<NetworkDevice> findByIdAndIsActiveIsTrue(Integer id);
    Boolean existsBySerialNumberAndIsActiveIsTrue(String serialNumber);
}
