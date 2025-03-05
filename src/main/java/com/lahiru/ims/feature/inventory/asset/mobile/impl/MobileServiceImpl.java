package com.lahiru.ims.feature.inventory.asset.mobile.impl;


import com.lahiru.ims.common.GenericDao;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.feature.AssetResponseDto;
import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.inventory.asset.mobile.Mobile;
import com.lahiru.ims.feature.inventory.asset.mobile.MobileRepo;
import com.lahiru.ims.feature.inventory.asset.mobile.MobileService;
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
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class MobileServiceImpl implements MobileService {
    private final MobileRepo mobileRepo;
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
    public Mobile findOne(Integer id) throws Exception {
        return mobileRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(MOBILE));
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
    public List<MobileAssetResponseDto> search(String serialNumber) throws Exception {
        return List.of();
    }

    @Override
    public PaginationResponse<MobileAssetResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Mobile> mobilePage = mobileRepo.findAllByPageWise(pageable);
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
        List<Mobile> activeMobile = mobileRepo.findAllActive();
        List<MobileAssetResponseDto> mobileResponse = modelMapper.map(activeMobile, new TypeToken<List<AssetResponseDto>>() {
        }.getType());
        return (!mobileResponse.isEmpty()) ? mobileResponse : Collections.emptyList();
    }

    @Override
    public MobileAssetResponseDto createOne(MobileAssetRequestDto mobileAssetRequestDto) throws Exception {
        Mobile mobile = convertToModel(mobileAssetRequestDto);
        Mobile saveMobile = mobileRepo.save(mobile);
        return convertToDto(saveMobile);
    }

    @Override
    public MobileAssetResponseDto updateOne(int id, MobileAssetRequestDto mobileAssetRequestDto) throws Exception {
        Mobile foundMobile = mobileRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(MOBILE));
        Mobile updateMobile = convertToModel(mobileAssetRequestDto);
        updateMobile.setId(foundMobile.getId());
        Mobile updatedMobile = mobileRepo.saveAndFlush(updateMobile);
        return convertToDto(updatedMobile);
    }

    @Override
    public MobileAssetResponseDto deleteOne(int id) throws Exception {
        Mobile deleteMobile = mobileRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(MOBILE));
        mobileRepo.softDeleteById(deleteMobile.getId());
        return convertToDto(deleteMobile);
    }
    @Override
    public Mobile convertToModel(MobileAssetRequestDto mobileAssetRequestDto) throws Exception {
        Mobile mobile = new Mobile();

        mobile.setInvoiceNumber(mobileAssetRequestDto.getInvoiceNumber());
        mobile.setPurchaseDate(mobileAssetRequestDto.getPurchaseDate());
        mobile.setWarrantyExpireDate(mobileAssetRequestDto.getWarrantyExpireDate());
        mobile.setAssetNumber(mobileAssetRequestDto.getAssetNumber());
        mobile.setSerialNumber(mobileAssetRequestDto.getSerialNumber());

        Manufacturer manufacturer = genericDao.checkAndCreate(AssetType.MOBILE, mobileAssetRequestDto.getManufacturer(), manufacturerService);
        Type type = genericDao.checkAndCreate(AssetType.MOBILE, mobileAssetRequestDto.getType(), typeService);
        Model model = genericDao.checkAndCreate(AssetType.MOBILE, mobileAssetRequestDto.getModel(), modelService);
        Status status = statusService.findOne(mobileAssetRequestDto.getStatusId());
        Location location = locationService.findOne(mobileAssetRequestDto.getLocationId());
        Employee employee = genericDao.checkAndCreate(mobileAssetRequestDto.getEmployee(), employeeService);
        Vendor vendor = vendorService.findOne(mobileAssetRequestDto.getVendorId());
        mobile.setLocation(location);
        mobile.setManufacturer(manufacturer);
        mobile.setType(type);
        mobile.setModel(model);
        mobile.setEmployee(employee);
        mobile.setIsActive(true);
        mobile.setStatus(status);
        mobile.setVendor(vendor);
        return mobile;
    }

    @Override
    public MobileAssetResponseDto convertToDto(Mobile mobile) throws Exception {
        try {
            return modelMapper.map(mobile, MobileAssetResponseDto.class);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
