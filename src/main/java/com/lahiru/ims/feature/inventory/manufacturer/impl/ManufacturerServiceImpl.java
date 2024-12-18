package com.lahiru.ims.feature.inventory.manufacturer.impl;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.feature.inventory.manufacturer.Manufacturer;
import com.lahiru.ims.feature.inventory.manufacturer.ManufacturerRepo;
import com.lahiru.ims.feature.inventory.manufacturer.ManufacturerService;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerRequestDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepo repository;
    private final ModelMapper modelMapper;


    @Override
    public ManufacturerRequestDto createOne(String name, AssetType assetType) throws Exception {
        try {
            AssetType validAssetType = AssetType.valueOf(assetType.toString().toUpperCase());
            Manufacturer saveType = repository.save(new Manufacturer(name, validAssetType));
            return modelMapper.map(saveType, ManufacturerRequestDto.class);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public ManufacturerRequestDto findOne(Integer id) throws Exception {
        Manufacturer manufacturer = repository.findById(id).orElseThrow(RuntimeException::new);
        return modelMapper.map(manufacturer, ManufacturerRequestDto.class);
    }

    @Override
    public List<ManufacturerRequestDto> getAll(AssetType assetType) throws Exception {
        List<Manufacturer> manufacturers = repository.findAllByAssetType(assetType);
        return (!manufacturers.isEmpty()) ? modelMapper
                .map(manufacturers, new TypeToken<List<ManufacturerRequestDto>>() {
                }
                        .getType()) : Collections.emptyList();
    }
}
