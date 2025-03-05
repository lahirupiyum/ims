package com.lahiru.ims.feature.inventory.type.impl;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.inventory.type.Type;
import com.lahiru.ims.feature.inventory.type.TypeRepo;
import com.lahiru.ims.feature.inventory.type.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final String TYPE = "Type";
    private final TypeRepo repository;

    @Override
    public Type createOne(String name, AssetType assetType) throws Exception {
        try {
            AssetType validAssetType = AssetType.valueOf(assetType.toString().toUpperCase());
            return repository.save(new Type(name, validAssetType));
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public Type findOne(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(TYPE));
    }

    @Override
    public List<Type> getAll(AssetType assetType) throws Exception {
        List<Type> typeList = repository.findAllByAssetType(assetType);
        return (!typeList.isEmpty()) ? typeList : Collections.emptyList();
    }
}
