package com.lahiru.ims.feature.customer.lastmile.provider;

import com.lahiru.ims.common.repository.IDNameRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LastMileProviderRepo extends IDNameRepo<LastMileProvider> {

}
