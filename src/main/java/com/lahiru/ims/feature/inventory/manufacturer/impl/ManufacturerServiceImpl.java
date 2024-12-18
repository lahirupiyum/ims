package com.lahiru.ims.feature.inventory.manufacturer.impl;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.inventory.manufacturer.Manufacturer;
import com.lahiru.ims.feature.inventory.manufacturer.ManufacturerRepo;
import com.lahiru.ims.feature.inventory.manufacturer.ManufacturerService;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerDto;
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
private final String MANUFACTURER="manufacture";

    @Override
    public ManufacturerDto createOne(String name, AssetType assetType) throws Exception {
        try {
            AssetType validAssetType = AssetType.valueOf(assetType.toString().toUpperCase());
            Manufacturer saveType = repository.save(new Manufacturer(name, validAssetType));
            return modelMapper.map(saveType, ManufacturerDto.class);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public ManufacturerDto findOne(Integer id) throws Exception {
        Manufacturer manufacturer = repository.findById(id).orElseThrow(RuntimeException::new);
        return modelMapper.map(manufacturer, ManufacturerDto.class);
    }

    @Override
    public List<ManufacturerDto> getAll(AssetType assetType) throws Exception {
        List<Manufacturer> manufacturers = repository.findAllByAssetType(assetType);
        return (!manufacturers.isEmpty()) ? modelMapper
                .map(manufacturers, new TypeToken<List<ManufacturerDto>>() {
                }
                        .getType()) : Collections.emptyList();
    }

    @Override
    public Manufacturer findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()->new NotFoundException(MANUFACTURER));
    }
}
