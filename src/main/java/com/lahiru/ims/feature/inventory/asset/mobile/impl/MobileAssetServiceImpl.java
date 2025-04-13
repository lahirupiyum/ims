package com.lahiru.ims.feature.inventory.asset.mobile.impl;


import com.lahiru.ims.common.GenericDao;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.feature.AssetResponseDto;
import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.inventory.asset.mobile.MobileAsset;
import com.lahiru.ims.feature.inventory.asset.mobile.MobileAssetRepo;
import com.lahiru.ims.feature.inventory.asset.mobile.MobileAssetService;
import com.lahiru.ims.feature.inventory.asset.mobile.dto.MobileAssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.mobile.dto.MobileAssetResponseDto;
import com.lahiru.ims.feature.inventory.employee.Employee;
import com.lahiru.ims.feature.inventory.employee.EmployeeService;
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
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MobileAssetServiceImpl implements MobileAssetService {
    private final MobileAssetRepo mobileAssetRepo;
    private final ModelMapper modelMapper;
    private final ModelService modelService;
    private final StatusService statusService;
    private final ManufacturerService manufacturerService;
    private final TypeService typeService;
    private final GenericDao genericDao;
    private final LocationService locationService;
    private final VendorService vendorService;
    public static final String MOBILE = "Mobile";
    public final EmployeeService employeeService;
    @Override
    public MobileAsset findOne(Integer id) throws Exception {
        return mobileAssetRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(MOBILE));
    }

    @Override
    public List<ModelDto> getAllModels() throws Exception {
        List<Model> modelList = modelService.getAll(AssetType.MOBILE);
        List<ModelDto> modelDtoList = modelMapper.map(modelList, new TypeToken<List<ModelDto>>() {
        }.getType());
        return (!modelDtoList.isEmpty()) ? modelDtoList : Collections.emptyList();
    }

    @Override
    public List<TypeDto> getAllTypes() throws Exception {
        List<Type> typeList = typeService.getAll(AssetType.MOBILE);
        List<TypeDto> typeDtoList = modelMapper.map(typeList, new TypeToken<List<TypeDto>>() {
        }.getType());
        return (!typeDtoList.isEmpty()) ? typeDtoList : Collections.emptyList();
    }

    @Override
    public List<StatusDto> getAllStatus() throws Exception {
        List<Status> statusList = statusService.getAll(AssetType.MOBILE);
        List<StatusDto> statusDtoList = modelMapper.map(statusList, new TypeToken<List<StatusDto>>() {
        }.getType());
        return (!statusDtoList.isEmpty()) ? statusDtoList : Collections.emptyList();
    }

    @Override
    public List<ManufacturerDto> getAllManufacturers() throws Exception {
        List<Manufacturer> manufacturerList = manufacturerService.getAll(AssetType.MOBILE);
        List<ManufacturerDto> manufacturerDtoList = modelMapper.map(manufacturerList, new TypeToken<List<StatusDto>>() {
        }.getType());
        return (!manufacturerDtoList.isEmpty()) ? manufacturerDtoList : Collections.emptyList();
    }

    @Override
    public List<MobileAssetResponseDto> search(String key) throws Exception {
        if (key.isEmpty()) return List.of();
        List<MobileAsset> mobileAssetList = mobileAssetRepo.search(key);
        return mobileAssetList.stream().map(mobileAsset -> {
            try {
                return convertToDto(mobileAsset);
            }
            catch (Exception ignored){
                return null;
            }
        }).filter(Objects::nonNull).toList();
    }

    @Override
    public PaginationResponse<MobileAssetResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<MobileAsset> mobilePage = mobileAssetRepo.findAllByPageWise(pageable);
        try {
            List<MobileAssetResponseDto> responseDtoList = modelMapper
                    .map(mobilePage.stream().toList(),
                            new TypeToken<List<MobileAssetResponseDto>>() {
                            }.getType());
            return new PaginationResponse<>(
                    responseDtoList,
                    (int) mobilePage.getTotalElements()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MobileAssetResponseDto> findAll() throws Exception {
        List<MobileAsset> activeMobileAsset = mobileAssetRepo.findAllActive();
        List<MobileAssetResponseDto> mobileResponse = modelMapper.map(activeMobileAsset, new TypeToken<List<AssetResponseDto>>() {
        }.getType());
        return (!mobileResponse.isEmpty()) ? mobileResponse : Collections.emptyList();
    }

    @Override
    public MobileAssetResponseDto createOne(MobileAssetRequestDto mobileAssetRequestDto) throws Exception {
        MobileAsset mobileAsset = convertToModel(mobileAssetRequestDto);
        MobileAsset saveMobileAsset = mobileAssetRepo.save(mobileAsset);
        return convertToDto(saveMobileAsset);
    }

    @Override
    public MobileAssetResponseDto updateOne(int id, MobileAssetRequestDto mobileAssetRequestDto) throws Exception {
        if (!mobileAssetRepo.isActiveById(id)) throw new NotFoundException(MOBILE);
        MobileAsset updateMobileAsset = convertToModel(mobileAssetRequestDto);
        updateMobileAsset.setId(id);
        MobileAsset updatedMobileAsset = mobileAssetRepo.saveAndFlush(updateMobileAsset);
        return convertToDto(updatedMobileAsset);
    }

    @Override
    public MobileAssetResponseDto deleteOne(int id) throws Exception {
        MobileAsset deleteMobileAsset = mobileAssetRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(MOBILE));
        mobileAssetRepo.softDeleteById(deleteMobileAsset.getId());
        return convertToDto(deleteMobileAsset);
    }
    @Override
    public MobileAsset convertToModel(MobileAssetRequestDto mobileAssetRequestDto) throws Exception {
        MobileAsset mobileAsset = new MobileAsset();

        mobileAsset.setInvoiceNumber(mobileAssetRequestDto.getInvoiceNumber());
        mobileAsset.setPurchaseDate(mobileAssetRequestDto.getPurchaseDate());
        mobileAsset.setWarrantyExpireDate(mobileAssetRequestDto.getWarrantyExpireDate());
        mobileAsset.setAssetNumber(mobileAssetRequestDto.getAssetNumber());
        mobileAsset.setSerialNumber(mobileAssetRequestDto.getSerialNumber());

        Manufacturer manufacturer = genericDao.checkAndCreate(AssetType.MOBILE, mobileAssetRequestDto.getManufacturer(), manufacturerService);
        Type type = genericDao.checkAndCreate(AssetType.MOBILE, mobileAssetRequestDto.getType(), typeService);
        Model model = genericDao.checkAndCreate(AssetType.MOBILE, mobileAssetRequestDto.getModel(), modelService);
        Status status = statusService.findOne(mobileAssetRequestDto.getStatusId());
        Location location = locationService.findOne(mobileAssetRequestDto.getLocationId());
        Employee employee = genericDao.checkAndCreate(mobileAssetRequestDto.getEmployee(), employeeService);
        Vendor vendor = vendorService.findOne(mobileAssetRequestDto.getVendorId());
        mobileAsset.setLocation(location);
        mobileAsset.setManufacturer(manufacturer);
        mobileAsset.setType(type);
        mobileAsset.setModel(model);
        mobileAsset.setEmployee(employee);
        mobileAsset.setIsActive(true);
        mobileAsset.setStatus(status);
        mobileAsset.setVendor(vendor);
        return mobileAsset;
    }

    @Override
    public MobileAssetResponseDto convertToDto(MobileAsset mobileAsset) throws Exception {
        try {
            return modelMapper.map(mobileAsset, MobileAssetResponseDto.class);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
