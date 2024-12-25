package com.lahiru.ims.feature.customer.service.connection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepo extends JpaRepository<Connection, Integer> {
}
