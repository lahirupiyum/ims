package com.lahiru.ims.feature.inventory.asset.fixed.impl;


import com.lahiru.ims.common.GenericDao;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.inventory.asset.fixed.Fixed;
import com.lahiru.ims.feature.inventory.asset.fixed.FixedRepo;
import com.lahiru.ims.feature.inventory.asset.fixed.FixedService;
import com.lahiru.ims.feature.inventory.asset.fixed.dto.FixedAssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.fixed.dto.FixedAssetResponseDto;
import com.lahiru.ims.feature.inventory.location.Location;
import com.lahiru.ims.feature.inventory.location.LocationService;
import com.lahiru.ims.feature.inventory.manufacturer.Manufacturer;
import com.lahiru.ims.feature.inventory.manufacturer.ManufacturerService;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerDto;
import com.lahiru.ims.feature.inventory.model.Model;
import com.lahiru.ims.feature.inventory.model.ModelService;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.Status;
import com.lahiru.ims.feature.inventory.status.StatusService;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.Type;
import com.lahiru.ims.feature.inventory.type.TypeService;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import com.lahiru.ims.feature.inventory.vendor.Vendor;
import com.lahiru.ims.feature.inventory.vendor.VendorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FixedServiceImpl implements FixedService {

    private final String FIXED = "Fixed";
    private final ManufacturerService manufacturerService;
    private final TypeService typeService;
    private final ModelService modelService;
    private final StatusService statusService;
    private final VendorService vendorService;
    private final LocationService locationService;
    private final GenericDao genericDao;
    private final FixedRepo repository;
    private final ModelMapper modelMapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final FixedRepo fixedRepo;

    @Override
    public PaginationResponse<FixedAssetResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Fixed> fixedPage = repository.findAllByPageWise(pageable);
        log.info("Fixed : {}", fixedPage);
        fixedPage.forEach(fixed -> {
            Hibernate.initialize(fixed.getVendor());
            Hibernate.initialize(fixed.getLocation());
            Hibernate.initialize(fixed.getManufacturer());
            Hibernate.initialize(fixed.getModel());
            Hibernate.initialize(fixed.getType());
            Hibernate.initialize(fixed.getStatus());
        });
        List<FixedAssetResponseDto> fixedResponses = fixedPage.getContent().stream()
                .map(fixed -> modelMapper.map(fixed, FixedAssetResponseDto.class))
                .toList();
        return new PaginationResponse<>(
                fixedResponses,
                (int) fixedPage.getTotalElements()
        );
    }

    @Override
    public List<FixedAssetResponseDto> findAll() throws Exception {
        List<Fixed> fixedList = repository.findAllActive();
        List<FixedAssetResponseDto> fixedResponse = modelMapper.map(fixedList, new TypeToken<List<FixedAssetResponseDto>>() {
        }.getType());
        return (!fixedResponse.isEmpty()) ? fixedResponse : Collections.emptyList();
    }

    @Transactional
    @Override
    public FixedAssetResponseDto createOne(FixedAssetRequestDto fixedAssetRequestDto) throws Exception {
        Fixed savedFixed = repository.save(convertDtoToEntity(fixedAssetRequestDto));
        log.info("Fixed Asset Saved Successful! ");
        // Step 4: Convert saved entity back to DTO and return
        return modelMapper.map(savedFixed, FixedAssetResponseDto.class);
    }


    @Override
    public FixedAssetResponseDto updateOne(int id, FixedAssetRequestDto fixedAssetRequestDto) throws Exception {
        Fixed fixedUpdate = repository.findActiveOne(id).orElseThrow(() -> new NotFoundException(FIXED));
        Fixed fixedUpdated = convertDtoToEntity(fixedAssetRequestDto);
        fixedUpdated.setId(fixedUpdate.getId());
        Fixed fixed = repository.saveAndFlush(fixedUpdated);
        return modelMapper.map(fixed, FixedAssetResponseDto.class);
    }
    public Fixed convertDtoToEntity(FixedAssetRequestDto dto) {
        Fixed fixed = new Fixed();
        try {
            Manufacturer manufacturer = genericDao.checkAndCreate(AssetType.FIXED, dto.getManufacturer(), manufacturerService);
            Type type = genericDao.checkAndCreate(AssetType.FIXED, dto.getType(), typeService);
            Model model = genericDao.checkAndCreate(AssetType.FIXED, dto.getModel(), modelService);
            Status status = statusService.findOne(dto.getStatusId());
            Location location = locationService.findOne(dto.getLocationId());
            Vendor vendor = vendorService.findOne(dto.getVendorId());
            fixed.setManufacturer(manufacturer);
            fixed.setType(type);
            fixed.setModel(model);
            fixed.setStatus(status);
            fixed.setLocation(location);
            fixed.setVendor(vendor);
            fixed.setIsActive(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        fixed.setDeprecationInfo(dto.getDeprecationInfo());
        fixed.setInvoiceNumber(dto.getInvoiceNumber());
        fixed.setPurchaseDate(dto.getPurchaseDate());
        fixed.setAssetNumber(dto.getAssetNumber());
        fixed.setSerialNumber(dto.getSerialNumber());
        return fixed;
    }
    @Override
    public FixedAssetResponseDto deleteOne(int id) throws Exception {
        Fixed foundDeleteFixed = repository.findActiveOne(id).orElseThrow(() -> new NotFoundException(FIXED));
        repository.deleteById(foundDeleteFixed.getId());
        log.info("Delete Fixed Asset ID: {}", id);
        return modelMapper.map(foundDeleteFixed, FixedAssetResponseDto.class);
    }



    // Get Model , Type ,Status
    @Override
    public List<ModelDto> getAllModels() throws Exception {
            List<Model> modelList = modelService.getAll(AssetType.FIXED);
            List<ModelDto> modelDtoList = modelMapper.map(modelList, new TypeToken<List<ModelDto>>() {
            }.getType());
            return (!modelDtoList.isEmpty()) ? modelDtoList : Collections.emptyList();
    }

    @Override
    public List<TypeDto> getAllTypes() throws Exception {
            List<Type> typeList = typeService.getAll(AssetType.FIXED);
            List<TypeDto> typeDtoList = modelMapper.map(typeList, new TypeToken<List<TypeDto>>() {
            }.getType());
            return (!typeDtoList.isEmpty()) ? typeDtoList : Collections.emptyList();
    }


    @Override
    public List<StatusDto> getAllStatus() throws Exception {
            List<Status> statusList = statusService.getAll(AssetType.FIXED);
            List<StatusDto> statusDtoList = modelMapper.map(statusList, new TypeToken<List<StatusDto>>() {
            }.getType());
            return (!statusDtoList.isEmpty()) ? statusDtoList : Collections.emptyList();
    }

    @Override
    public List<ManufacturerDto> getAllManufacturers() throws Exception {
        List<Manufacturer> manufacturerList = manufacturerService.getAll(AssetType.FIXED);
        return modelMapper.map(manufacturerList, new TypeToken<List<ManufacturerDto>>(){}.getType());
    }

    @Override
    public List<FixedAssetResponseDto> search(String key) throws Exception {
        if (key.isEmpty()) return List.of();
        List<Fixed> fixedList = fixedRepo.search(key);
        return fixedList.stream().map(fixed -> modelMapper.map(fixed, FixedAssetResponseDto.class)).toList();
    }

}
