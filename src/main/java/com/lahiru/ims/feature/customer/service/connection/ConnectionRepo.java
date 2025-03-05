package com.lahiru.ims.feature.customer.service.connection;

import com.lahiru.ims.feature.customer.router.firewallcredentials.RouterFirewallCredentials;
import com.lahiru.ims.feature.customer.service.enums.NetworkServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConnectionRepo extends JpaRepository<Connection, Integer> {
    Page<Connection> findByServiceType(NetworkServiceType serviceType, Pageable pageable);
    Optional<Connection> findByRouterFirewallCredentials(RouterFirewallCredentials firewallCredentials);
}
