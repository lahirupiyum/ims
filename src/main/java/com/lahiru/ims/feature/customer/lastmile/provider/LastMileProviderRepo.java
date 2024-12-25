package com.lahiru.ims.feature.customer.lastmile.provider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LastMileProviderRepo extends JpaRepository<LastMileProvider, Integer> {
}
