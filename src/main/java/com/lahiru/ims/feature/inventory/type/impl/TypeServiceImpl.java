package com.lahiru.ims.feature.inventory.type.impl;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerRequestDto;
import com.lahiru.ims.feature.inventory.type.Type;
import com.lahiru.ims.feature.inventory.type.TypeRepo;
import com.lahiru.ims.feature.inventory.type.TypeService;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final ModelMapper modelMapper;
    private final TypeRepo repository;

    @Override
    public TypeDto createOne(String name, AssetType assetType) throws Exception {
        try {
            AssetType validAssetType = AssetType.valueOf(assetType.toString().toUpperCase());
            Type saveType = repository.save(new Type(name, validAssetType));
            return modelMapper.map(saveType, TypeDto.class);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public TypeDto findOne(Integer id) throws Exception {
        Type type = repository.findById(id).orElseThrow(RuntimeException::new);
        return modelMapper.map(type, TypeDto.class);
    }

    @Override
    public List<TypeDto> getAll(AssetType assetType) throws Exception {
        List<Type> types = repository.findAllByAssetType(assetType);
        return (!types.isEmpty()) ? modelMapper
                .map(types, new TypeToken<List<TypeDto>>() {
                }
                        .getType()) : Collections.emptyList();
    }
}
