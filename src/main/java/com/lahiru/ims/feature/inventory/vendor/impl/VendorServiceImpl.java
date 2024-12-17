package com.lahiru.ims.feature.inventory.vendor.impl;


import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.DataConflictException;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.inventory.vendor.Vendor;
import com.lahiru.ims.feature.inventory.vendor.VendorMappers;
import com.lahiru.ims.feature.inventory.vendor.VendorRepo;
import com.lahiru.ims.feature.inventory.vendor.VendorService;
import com.lahiru.ims.feature.inventory.vendor.dto.VendorRequestDto;
import com.lahiru.ims.feature.inventory.vendor.dto.VendorResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {
    public static final String VENDOR = "Vendor";
    public static final String VENDOR_EMAIL = "Vendor email";
    private static final Logger log = LoggerFactory.getLogger(VendorServiceImpl.class);
    private final VendorRepo vendorRepo;
    private  final VendorMappers vendorMappers;
    private final boolean activeStatus = true;
    private final ModelMapper modelMapper;
    @Override
    public PaginationResponse<VendorResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Vendor> vendorPage = vendorRepo.findAllByIsActive(pageable, activeStatus);
        log.info("vendor: {}", vendorPage);
        List<VendorResponseDto> vendorList = vendorPage.map(vendorMappers::toResponseDto).toList();
        log.info("vendor: {}", vendorList);
        return new PaginationResponse<>(
                vendorList,
                (int) vendorPage.getTotalElements()
        );

    }

    @Override
    public List<VendorResponseDto> findAll() throws Exception {
//
        return null;
    }

    @Override
    public VendorResponseDto createOne(VendorRequestDto requestDto) throws Exception {

        if (isVendorExistsByEmail(requestDto.getEmail()))
            throw new DataConflictException(VENDOR_EMAIL);
//        log.info("vendor:{}",requestDto.getContactNo());
//        Vendor vendor = vendorMappers.toEntity(requestDto);
//        log.info("vendor:{}",vendor.getContactNo());
//        Vendor savedVendor = vendorRepo.save(vendor);
        Vendor vendor =modelMapper.map(requestDto,Vendor.class);
        Vendor saveVendor = vendorRepo.save(vendor);
        VendorResponseDto response =modelMapper.map(saveVendor,VendorResponseDto.class);
        return response;
    }

    @Override
    public VendorResponseDto updateOne(int id, VendorRequestDto requestDto) throws Exception {

        String updatedEmail = requestDto.getEmail();

        Vendor vendor = vendorExistsById(id);

        if (isVendorExistsByEmail(updatedEmail) && !vendor.getEmail().equals(updatedEmail))
            throw new DataConflictException(VENDOR_EMAIL);

        Vendor vendorToBeUpdated = vendorMappers.toEntity(requestDto);
        vendorToBeUpdated.setId(id);

        Vendor updatedVendor = vendorRepo.save(vendorToBeUpdated);
//
//        return VendorMapper.toDto(updatedVendor);
        return null;
    }

    @Override
    public VendorResponseDto deleteOne(int id) throws Exception {
        Vendor vendor = vendorExistsById(id);
        vendor.setActive(false);
        vendorRepo.save(vendor);

//        return VendorMapper.toDto(vendor);
        return null;
    }

    private Vendor vendorExistsById(Integer id) {
        return vendorRepo.findByIdAndIsActive(id, activeStatus)
                .orElseThrow(() -> new NotFoundException(VENDOR));
    }

    private boolean isVendorExistsByEmail(String email) {
        return vendorRepo.existsByEmailAndIsActive(email, activeStatus);
    }

}
