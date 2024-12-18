package com.lahiru.ims.features.inventory.vendor;


import com.lahiru.ims.common.GenericController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.features.inventory.vendor.dto.VendorRequestDto;
import com.lahiru.ims.features.inventory.vendor.dto.VendorResponseDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${endpoints.vendor}")
@RequiredArgsConstructor
public class VendorController implements GenericController<VendorRequestDto, VendorResponseDto> {

    private final VendorService vendorService;

    @Override
    public ResponseEntity<PaginationResponse<VendorResponseDto>> getAllByPageWise(int page,
                                                                                  int pageSize) throws Exception {
        PaginationResponse<VendorResponseDto> vendorPage = vendorService.findByPageWise(page, pageSize);
        return ResponseEntityManager.page(vendorPage);
    }

    @Override
    public ResponseEntity<StandardReponse<List<VendorResponseDto>>> getAll() throws Exception {
        List<VendorResponseDto> vendors = vendorService.findAll();
        return ResponseEntityManager.ok(vendors);
    }

    @Override
    public ResponseEntity<StandardReponse<VendorResponseDto>> createOne(VendorRequestDto requestDto)
            throws Exception {
        VendorResponseDto vendor = vendorService.createOne(requestDto);
        return ResponseEntityManager.created(vendor, "Vendor");
    }

    @Override
    public ResponseEntity<StandardReponse<VendorResponseDto>> updateOne(int id, @Valid VendorRequestDto requestDto)
            throws Exception {
        VendorResponseDto vendor = vendorService.updateOne(id, requestDto);
        return ResponseEntityManager.ok(vendor);
    }

    @Override
    public ResponseEntity<StandardReponse<VendorResponseDto>> deleteOne(int id) throws Exception {
        VendorResponseDto deletedVendor = vendorService.deleteOne(id);
        return ResponseEntityManager.ok(deletedVendor);
    }

}
