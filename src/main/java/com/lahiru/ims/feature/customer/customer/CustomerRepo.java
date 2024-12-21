package com.lahiru.ims.feature.customer.customer;

import com.lahiru.ims.common.repository.StatusAwareRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends StatusAwareRepo<Customer> {
    @Query("SELECT COUNT(c) > 0 FROM Customer c WHERE c.email = :email AND c.isActive = true")
    Boolean isActiveByEmail(String email);
}
