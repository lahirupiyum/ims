package com.lahiru.ims.feature.inventory.status.impl;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.inventory.status.Status;
import com.lahiru.ims.feature.inventory.status.StatusRepo;
import com.lahiru.ims.feature.inventory.status.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepo repository;
    private final String STATUS = "Status";

    @Override
    public Status createOne(String name, AssetType assetType) throws Exception {
        return null;
    }

    @Override
    public Status findOne(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(STATUS));
    }

    @Override
    public List<Status> getAll(AssetType assetType) throws Exception {
        List<Status> statusList = repository.findAll();
        return (!statusList.isEmpty()) ? statusList : Collections.emptyList();
    }
}
