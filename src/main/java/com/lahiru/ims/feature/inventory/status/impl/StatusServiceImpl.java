package com.lahiru.ims.feature.inventory.status.impl;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.feature.inventory.status.Status;
import com.lahiru.ims.feature.inventory.status.StatusRepo;
import com.lahiru.ims.feature.inventory.status.StatusService;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.Type;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final ModelMapper modelMapper;
    private final StatusRepo repository;
    @Override
    public StatusDto createOne(String name, AssetType assetType) throws Exception {
        try {
            AssetType validAssetType = AssetType.valueOf(assetType.toString().toUpperCase());
            Status saveStatus = repository.save(new Status(name, validAssetType));
            return modelMapper.map(saveStatus, StatusDto.class);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public StatusDto findOne(Integer id) throws Exception {
        Status type = repository.findById(id).orElseThrow(RuntimeException::new);
        return modelMapper.map(type, StatusDto.class);
    }

    @Override
    public List<StatusDto> getAll(AssetType assetType) throws Exception {
        List<Status> status = repository.findAllByAssetType(assetType);
        return (!status.isEmpty()) ? modelMapper
                .map(status, new TypeToken<List<StatusDto>>() {
                }
                        .getType()) : Collections.emptyList();
    }

    @Override
    public Status findById(Integer id) throws Exception {
        return repository.findById(id).get();
    }
}
