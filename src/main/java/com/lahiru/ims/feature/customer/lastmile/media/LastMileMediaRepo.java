package com.lahiru.ims.feature.customer.lastmile.media;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LastMileMediaRepo extends JpaRepository<LastMileMedia, Integer> {
}
