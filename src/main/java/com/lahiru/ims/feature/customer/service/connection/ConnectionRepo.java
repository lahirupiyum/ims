package com.lahiru.ims.feature.customer.service.connection;

import com.lahiru.ims.feature.customer.service.enums.NetworkServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepo extends JpaRepository<Connection, Integer> {
    Page<Connection> findByServiceType(NetworkServiceType serviceType, Pageable pageable);
}
