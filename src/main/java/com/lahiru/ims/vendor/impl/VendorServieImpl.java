package com.lahiru.ims.vendor.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.vendor.Vendor;
import com.lahiru.ims.vendor.VendorMapper;
import com.lahiru.ims.vendor.VendorRepo;
import com.lahiru.ims.vendor.VendorService;
import com.lahiru.ims.vendor.dto.VendorRequestDto;
import com.lahiru.ims.vendor.dto.VendorResponseDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class VendorServieImpl implements VendorService {

    private final Logger logger = LoggerFactory.getLogger(VendorServieImpl.class);
    
    private final VendorRepo vendorRepo;
    @Value("${application.resource.vendor}")
    private String resource;

    private final boolean activeStatus = true;

    @Override
    public PaginationResponse<VendorResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Vendor> vendorPage = vendorRepo.findAll(pageable);
        List<VendorResponseDto> vendorList = vendorPage.map((vendor) -> VendorMapper.toDto(vendor)).toList();

        return new PaginationResponse<>(
            vendorList,
            (int) vendorPage.getTotalElements()
        );
    }

    @Override
    public List<VendorResponseDto> findAll() throws Exception {
        List<Vendor> vendorList = vendorRepo.findAll();
        return vendorList.stream().map((vendor) -> VendorMapper.toDto(vendor)).toList();
    }

    @Override
    public VendorResponseDto createOne(VendorRequestDto requestDto) throws Exception {
        Vendor vendor = VendorMapper.toModel(requestDto);
        Vendor savedVendor = vendorRepo.save(vendor);

        return VendorMapper.toDto(savedVendor);
    }

    @Override
    public VendorResponseDto updateOne(int id, VendorRequestDto requestDto) throws Exception {
        if(!vendorRepo.existsByIdAndStatus(id, activeStatus))
            throw new NotFoundException(resource);
        
        Vendor vendor = VendorMapper.toModel(requestDto);
        vendor.setId(id);

        Vendor updatedVendor = vendorRepo.save(vendor);
        
        return VendorMapper.toDto(updatedVendor);
    }

    @Override
    public VendorResponseDto deleteOne(int id) throws Exception {
       Vendor vendor = vendorRepo.findByIdAndStatus(id, activeStatus).orElseThrow(() -> new NotFoundException(resource));
       vendor.setStatus(false);
       vendorRepo.save(vendor);

       return VendorMapper.toDto(vendor);
    }
    
}
