package com.lahiru.ims.feature.inventory.asset.fixed.impl;


import com.lahiru.ims.common.GenericDao;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.inventory.asset.fixed.FixedAsset;
import com.lahiru.ims.feature.inventory.asset.fixed.FixedAssetRepo;
import com.lahiru.ims.feature.inventory.asset.fixed.FixedAssetService;
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
public class FixedAssetServiceImpl implements FixedAssetService {

    private final String FIXED = "Fixed";
    private final ManufacturerService manufacturerService;
    private final TypeService typeService;
    private final ModelService modelService;
    private final StatusService statusService;
    private final VendorService vendorService;
    private final LocationService locationService;
    private final GenericDao genericDao;
    private final FixedAssetRepo repository;
    private final ModelMapper modelMapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final FixedAssetRepo fixedRepo;

    @Override
    public PaginationResponse<FixedAssetResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<FixedAsset> fixedPage = repository.findAllByPageWise(pageable);
        log.info("Fixed : {}", fixedPage);
        fixedPage.forEach(fixedAsset -> {
            Hibernate.initialize(fixedAsset.getVendor());
            Hibernate.initialize(fixedAsset.getLocation());
            Hibernate.initialize(fixedAsset.getManufacturer());
            Hibernate.initialize(fixedAsset.getModel());
            Hibernate.initialize(fixedAsset.getType());
            Hibernate.initialize(fixedAsset.getStatus());
        });
        List<FixedAssetResponseDto> fixedResponses = fixedPage.getContent().stream()
                .map(fixedAsset -> modelMapper.map(fixedAsset, FixedAssetResponseDto.class))
                .toList();
        return new PaginationResponse<>(
                fixedResponses,
                (int) fixedPage.getTotalElements()
        );
    }

    @Override
    public List<FixedAssetResponseDto> findAll() throws Exception {
        List<FixedAsset> fixedAssetList = repository.findAllActive();
        List<FixedAssetResponseDto> fixedResponse = modelMapper.map(fixedAssetList, new TypeToken<List<FixedAssetResponseDto>>() {
        }.getType());
        return (!fixedResponse.isEmpty()) ? fixedResponse : Collections.emptyList();
    }

    @Transactional
    @Override
    public FixedAssetResponseDto createOne(FixedAssetRequestDto fixedAssetRequestDto) throws Exception {
        FixedAsset savedFixedAsset = repository.save(convertDtoToEntity(fixedAssetRequestDto));
        log.info("Fixed Asset Saved Successful! ");
        // Step 4: Convert saved entity back to DTO and return
        return modelMapper.map(savedFixedAsset, FixedAssetResponseDto.class);
    }


    @Override
    public FixedAssetResponseDto updateOne(int id, FixedAssetRequestDto fixedAssetRequestDto) throws Exception {
        FixedAsset fixedAssetUpdate = repository.findActiveOne(id).orElseThrow(() -> new NotFoundException(FIXED));
        FixedAsset fixedAssetUpdated = convertDtoToEntity(fixedAssetRequestDto);
        fixedAssetUpdated.setId(fixedAssetUpdate.getId());
        FixedAsset fixedAsset = repository.saveAndFlush(fixedAssetUpdated);
        return modelMapper.map(fixedAsset, FixedAssetResponseDto.class);
    }
    public FixedAsset convertDtoToEntity(FixedAssetRequestDto dto) {
        FixedAsset fixedAsset = new FixedAsset();
        try {
            Manufacturer manufacturer = genericDao.checkAndCreate(AssetType.FIXED, dto.getManufacturer(), manufacturerService);
            Type type = genericDao.checkAndCreate(AssetType.FIXED, dto.getType(), typeService);
            Model model = genericDao.checkAndCreate(AssetType.FIXED, dto.getModel(), modelService);
            Status status = statusService.findOne(dto.getStatusId());
            Location location = locationService.findOne(dto.getLocationId());
            Vendor vendor = vendorService.findOne(dto.getVendorId());
            fixedAsset.setManufacturer(manufacturer);
            fixedAsset.setType(type);
            fixedAsset.setModel(model);
            fixedAsset.setStatus(status);
            fixedAsset.setLocation(location);
            fixedAsset.setVendor(vendor);
            fixedAsset.setIsActive(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        fixedAsset.setDeprecationInfo(dto.getDeprecationInfo());
        fixedAsset.setInvoiceNumber(dto.getInvoiceNumber());
        fixedAsset.setPurchaseDate(dto.getPurchaseDate());
        fixedAsset.setAssetNumber(dto.getAssetNumber());
        fixedAsset.setSerialNumber(dto.getSerialNumber());
        return fixedAsset;
    }
    @Override
    public FixedAssetResponseDto deleteOne(int id) throws Exception {
        FixedAsset foundDeleteFixedAsset = repository.findActiveOne(id).orElseThrow(() -> new NotFoundException(FIXED));
        repository.deleteById(foundDeleteFixedAsset.getId());
        log.info("Delete Fixed Asset ID: {}", id);
        return modelMapper.map(foundDeleteFixedAsset, FixedAssetResponseDto.class);
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
        List<FixedAsset> fixedAssetList = fixedRepo.search(key);
        return fixedAssetList.stream().map(fixedAsset -> modelMapper.map(fixedAsset, FixedAssetResponseDto.class)).toList();
    }

}
