package com.lahiru.ims.feature.inventory.asset.fixed.impl;


import com.lahiru.ims.common.GenericDao;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.feature.AssetRequestDto;
import com.lahiru.ims.common.dto.feature.AssetResponseDto;
import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.feature.inventory.asset.fixed.Fixed;
import com.lahiru.ims.feature.inventory.asset.fixed.FixedRepo;
import com.lahiru.ims.feature.inventory.asset.fixed.FixedService;
import com.lahiru.ims.feature.inventory.location.LocationService;
import com.lahiru.ims.feature.inventory.manufacturer.ManufacturerService;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerDto;
import com.lahiru.ims.feature.inventory.model.ModelService;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.StatusService;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.TypeService;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import com.lahiru.ims.feature.inventory.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FixedServiceImpl implements FixedService {
    private final ManufacturerService manufacturerService;
    private final TypeService typeService;
    private final ModelService modelService;
    private final StatusService statusService;
    private final VendorService vendorService;
    private final LocationService locationService;
    private final GenericDao genericDao;
    private final FixedRepo repository;
    private final ModelMapper modelMapper;
    @Override
    public PaginationResponse<AssetResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        return null;
    }
    @Override
    public List<AssetResponseDto> findAll() throws Exception {
        return List.of();
    }

    @Override
    public AssetResponseDto createOne(AssetRequestDto assetRequestDto) throws Exception {
        ManufacturerDto manufacturerDto = genericDao.checkAndCreate(AssetType.FIXED, assetRequestDto.getManufacturer(), manufacturerService);
        TypeDto typeDto = genericDao.checkAndCreate(AssetType.FIXED, assetRequestDto.getType(), typeService);
        ModelDto modelDto = genericDao.checkAndCreate(AssetType.FIXED, assetRequestDto.getModel(), modelService);
        StatusDto statusDto = genericDao.checkAndCreate(AssetType.FIXED, assetRequestDto.getStatus(), statusService);
//
//        Vendor vendor, Location location,
//

        Fixed fixed =modelMapper.map(assetRequestDto,Fixed.class);
        fixed.setManufacturer(manufacturerService.findById(manufacturerDto.getId()));
        fixed.setModel(modelService.findById(modelDto.getId()));
        fixed.setStatus(statusService.findById(statusDto.getId()));
        fixed.setType(typeService.findById(typeDto.getId()));

        Fixed saveFixed = repository.save(fixed);

        return modelMapper.map(saveFixed,AssetResponseDto.class);
    }

    @Override
    public AssetResponseDto updateOne(int id, AssetRequestDto assetRequestDto) throws Exception {
        return null;
    }

    @Override
    public AssetResponseDto deleteOne(int id) throws Exception {
        return null;
    }
}
