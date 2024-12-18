package com.lahiru.ims.feature.inventory.vendor.impl;


import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.DataConflictException;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.inventory.vendor.Vendor;
import com.lahiru.ims.feature.inventory.vendor.VendorRepo;
import com.lahiru.ims.feature.inventory.vendor.VendorService;
import com.lahiru.ims.feature.inventory.vendor.dto.VendorRequestDto;
import com.lahiru.ims.feature.inventory.vendor.dto.VendorResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {
    public static final String VENDOR = "Vendor";
    public static final String VENDOR_EMAIL = "Vendor email";
    private static final Logger log = LoggerFactory.getLogger(VendorServiceImpl.class);
    private final VendorRepo vendorRepo;
    //    private  final VendorMappers vendorMappers;
    private final boolean activeStatus = true;
    private final ModelMapper modelMapper;
    @Override
    public PaginationResponse<VendorResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Vendor> vendorPage = vendorRepo.findAllByIsActive(pageable, activeStatus);
        log.info("vendor: {}", vendorPage);
        List<VendorResponseDto> vendorList = vendorPage.stream()
                .map(vendor -> modelMapper.map(vendor, VendorResponseDto.class))
                .collect(Collectors.toList());
        log.info("vendor: {}", vendorList);
        return new PaginationResponse<>(
                vendorList,
                (int) vendorPage.getTotalElements()
        );
    }

    @Override
    public List<VendorResponseDto> findAll() throws Exception {
        //Using ModelMapper
        try {
            return modelMapper.map(vendorRepo.findAll(), new TypeToken<List<VendorResponseDto>>() {
            }.getType());
        } catch (Exception e) {
            throw new NotFoundException("Data Lost  in data Base");
        }

    }

    @Override
    public VendorResponseDto createOne(VendorRequestDto requestDto) throws Exception {
        if (isVendorExistsByEmail(requestDto.getEmail()))
            throw new DataConflictException(VENDOR_EMAIL);
        Vendor vendor =modelMapper.map(requestDto,Vendor.class);
        Vendor saveVendor = vendorRepo.save(vendor);
        log.info("Saved Vendor Successfully; {}", saveVendor);
        return modelMapper.map(saveVendor, VendorResponseDto.class);
    }

    @Override
    public VendorResponseDto updateOne(int id, VendorRequestDto requestDto) throws Exception {
        String updatedEmail = requestDto.getEmail();
        Vendor vendor = vendorExistsById(id);
        if (isVendorExistsByEmail(updatedEmail) && !vendor.getEmail().equals(updatedEmail))
            throw new DataConflictException(VENDOR_EMAIL);
        vendor.setName(requestDto.getName());
        vendor.setEmail(requestDto.getEmail());
        vendor.setContactNo(requestDto.getContactNo());
        return modelMapper.map(vendorRepo.saveAndFlush(vendor),VendorResponseDto.class);
    }

    @Override
    public VendorResponseDto deleteOne(int id) throws Exception {
        Vendor vendor = vendorExistsById(id);
        vendor.setActive(false);
        Vendor saveVendor = vendorRepo.save(vendor);
        return modelMapper.map(saveVendor,VendorResponseDto.class);
    }

    private Vendor vendorExistsById(Integer id) {
        return vendorRepo.findByIdAndIsActive(id, activeStatus)
                .orElseThrow(() -> new NotFoundException(VENDOR));
    }

    private boolean isVendorExistsByEmail(String email) {
        return vendorRepo.existsByEmailAndIsActive(email, activeStatus);
    }

}
