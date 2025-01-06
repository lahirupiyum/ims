package com.lahiru.ims.feature.inventory.manufacturer.impl;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.inventory.manufacturer.Manufacturer;
import com.lahiru.ims.feature.inventory.manufacturer.ManufacturerRepo;
import com.lahiru.ims.feature.inventory.manufacturer.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepo repository;
    private final ModelMapper modelMapper;
    private final String MANUFACTURER = "manufacture";

    @Override
    public Manufacturer createOne(String name, AssetType assetType) throws Exception {
        try {
            AssetType validAssetType = AssetType.valueOf(assetType.toString().toUpperCase());
            return repository.save(new Manufacturer(name, validAssetType));
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public Manufacturer findOne(Integer id) throws Exception {

        return repository.findById(id).orElseThrow(() -> new NotFoundException(MANUFACTURER));
    }

    @Override
    public List<Manufacturer> getAll(AssetType assetType) throws Exception {
        return repository.findAllByAssetType(assetType);
    }
}
