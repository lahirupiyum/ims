package com.lahiru.ims.common.service;

import com.lahiru.ims.common.dto.feature.BasicInfo;
import com.lahiru.ims.common.model.BasicCustomerAudit;

import java.util.List;

public interface GenericBasicCustomerService<Model extends BasicCustomerAudit, Dto extends BasicInfo> extends EntityFinderService<Model> {
    Model createOne(String name) throws Exception;
    List<Dto> getAll() throws Exception;
}
