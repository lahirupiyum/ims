package com.lahiru.ims.feature.customer.customer;

import com.lahiru.ims.common.repository.StatusAwareRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends StatusAwareRepo<Customer> {
    @Query("SELECT COUNT(c) > 0 FROM Customer c WHERE c.email = :email AND c.isActive = true")
    Boolean isActiveByEmail(String email);

    List<Customer> findAllByNameContaining(String name);

    @Query("SELECT c FROM Customer c WHERE c.isActive = true AND (" +
            "c.name LIKE %:key% OR " +
            "c.accountManager.name LIKE %:key% OR " +
            "c.address LIKE %:key% OR " +
            "c.email LIKE %:key% OR " +
            "c.vsnlId LIKE %:key% OR " +
            "c.contactNo LIKE %:key% )")
    List<Customer> search(String key);
}
