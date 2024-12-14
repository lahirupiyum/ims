package com.lahiru.ims.features.inventory.vendor.impl;


import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.DataConflictException;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.features.inventory.vendor.Vendor;
import com.lahiru.ims.features.inventory.vendor.VendorMapper;
import com.lahiru.ims.features.inventory.vendor.VendorRepo;
import com.lahiru.ims.features.inventory.vendor.VendorService;
import com.lahiru.ims.features.inventory.vendor.dto.VendorRequestDto;
import com.lahiru.ims.features.inventory.vendor.dto.VendorResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    private final VendorRepo vendorRepo;

    private final boolean activeStatus = true;

    @Override
    public PaginationResponse<VendorResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Vendor> vendorPage = vendorRepo.findAllByIsActive(pageable, activeStatus);
        List<VendorResponseDto> vendorList = vendorPage.map(VendorMapper::toDto).toList();

        return new PaginationResponse<>(
                vendorList,
                (int) vendorPage.getTotalElements()
        );
    }

    @Override
    public List<VendorResponseDto> findAll() throws Exception {
        List<Vendor> vendorList = vendorRepo.findAllByIsActive(activeStatus);
        return vendorList.stream().map(VendorMapper::toDto).toList();
    }

    @Override
    public VendorResponseDto createOne(VendorRequestDto requestDto) throws Exception {

        if (isVendorExistsByEmail(requestDto.getEmail()))
            throw new DataConflictException(VENDOR_EMAIL);

        Vendor vendor = VendorMapper.toModel(requestDto);
        Vendor savedVendor = vendorRepo.save(vendor);

        return VendorMapper.toDto(savedVendor);
    }

    @Override
    public VendorResponseDto updateOne(int id, VendorRequestDto requestDto) throws Exception {

        String updatedEmail = requestDto.getEmail();

        Vendor vendor = vendorExistsById(id);

        if (isVendorExistsByEmail(updatedEmail) && !vendor.getEmail().equals(updatedEmail))
            throw new DataConflictException(VENDOR_EMAIL);

        Vendor vendorToBeUpdated = VendorMapper.toModel(requestDto);
        vendorToBeUpdated.setId(id);

        Vendor updatedVendor = vendorRepo.save(vendorToBeUpdated);

        return VendorMapper.toDto(updatedVendor);
    }

    @Override
    public VendorResponseDto deleteOne(int id) throws Exception {
        Vendor vendor = vendorExistsById(id);
        vendor.setIsActive(false);
        vendorRepo.save(vendor);

        return VendorMapper.toDto(vendor);
    }

    private Vendor vendorExistsById(Integer id) {
        return vendorRepo.findByIdAndIsActive(id, activeStatus)
                .orElseThrow(() -> new NotFoundException(VENDOR));
    }

    private boolean isVendorExistsByEmail(String email) {
        return vendorRepo.existsByEmailAndIsActive(email, activeStatus);
    }

}
