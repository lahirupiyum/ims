package com.lahiru.ims.feature.inventory.vendor;


import com.lahiru.ims.common.controller.GenericController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.feature.inventory.vendor.dto.VendorRequestDto;
import com.lahiru.ims.feature.inventory.vendor.dto.VendorResponseDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${endpoints.vendor}")
@RequiredArgsConstructor
public class VendorController implements GenericController<VendorRequestDto, VendorResponseDto> {

    public static final String VENDOR = "Vendor";
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
        return ResponseEntityManager.created(vendor, VENDOR);
    }

    @Override
    public ResponseEntity<StandardReponse<VendorResponseDto>> updateOne(int id, @Valid VendorRequestDto requestDto)
            throws Exception {
        VendorResponseDto vendor = vendorService.updateOne(id, requestDto);
        return ResponseEntityManager.updated(vendor, VENDOR);
    }

    @Override
    public ResponseEntity<StandardReponse<VendorResponseDto>> deleteOne(int id) throws Exception {
        VendorResponseDto deletedVendor = vendorService.deleteOne(id);
        return ResponseEntityManager.deleted(deletedVendor, VENDOR);
    }

    @GetMapping("/search")
    public ResponseEntity<StandardReponse<List<VendorResponseDto>>> searchVendors(@RequestParam("key") String key) throws Exception {
        List<VendorResponseDto> vendorList = vendorService.searchItem(key);
        return ResponseEntityManager.ok(vendorList);
    }

}
