package com.lahiru.ims.feature.customer.service.connection;

import com.lahiru.ims.feature.customer.router.firewallcredentials.RouterFirewallCredentials;
import com.lahiru.ims.feature.customer.service.enums.NetworkServiceType;
import com.lahiru.ims.feature.inventory.asset.network.NetworkAsset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConnectionRepo extends JpaRepository<Connection, Integer> {
    Page<Connection> findByServiceType(NetworkServiceType serviceType, Pageable pageable);
    Optional<Connection> findByRouterFirewallCredentials(RouterFirewallCredentials firewallCredentials);

    @Query("SELECT c FROM Connection c WHERE c.serviceType = :networkServiceType AND (" +
            "c.lastMileConnection.circuitId LIKE %:key% OR " +
            "c.customer.name LIKE %:key% OR " +
            "c.customer.email LIKE %:key% OR " +
            "c.cusRouter.networkAsset.serialNumber LIKE %:key% OR " +
            "c.peRouterConnection.peRouter.serialNumber LIKE %:key% OR " +
            "c.peRouterConnection.peRouter.location.name  LIKE %:key% )")
    List<Connection> search(String key, NetworkServiceType networkServiceType);

    @Query("SELECT COUNT(c) > 0 FROM Connection c WHERE " +
            "(c.cusRouter.networkAsset = :asset " +
            "OR c.peRouterConnection.peRouter = :asset " +
            "OR c.peRouterConnection.networkAssetSwitch = :asset) " +
            "AND c.activeStatus = true")
    Boolean isActiveConnectionExistsByNetworkAsset(NetworkAsset asset);

    @Query("SELECT COUNT(c) > 0 FROM Connection c  WHERE c.cusRouter.networkAsset.id = :id AND c.activeStatus = true")
    Boolean isActiveConnectionExistsByCusRouterNetworkAsset(Integer id);
}
