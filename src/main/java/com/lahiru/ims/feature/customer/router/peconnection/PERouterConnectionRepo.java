package com.lahiru.ims.feature.customer.router.peconnection;

import com.lahiru.ims.common.repository.StatusAwareRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface PERouterConnectionRepo extends StatusAwareRepo<PERouterConnection> {
}
