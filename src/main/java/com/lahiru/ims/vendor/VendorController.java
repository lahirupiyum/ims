package com.lahiru.ims.vendor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lahiru.ims.common.GlobalController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.vendor.dto.VendorRequestDto;
import com.lahiru.ims.vendor.dto.VendorResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/${application.resource.vendor}")
@RequiredArgsConstructor
public class VendorController implements GlobalController<VendorRequestDto, VendorResponseDto> {

    private final VendorService vendorService;
    private final Logger logger = LoggerFactory.getLogger(VendorController.class);

    @Override
    public ResponseEntity<StandardReponse<PaginationResponse<VendorResponseDto>>> getAllByPageWise(int page,
            int pageSize) throws Exception {
        try {
           PaginationResponse<VendorResponseDto> vendorPage = vendorService.findByPageWise(page, pageSize);
           StandardReponse<PaginationResponse<VendorResponseDto>> standardReponse = new StandardReponse<>(200, "", vendorPage);
           return ResponseEntity.ok().body(standardReponse);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<StandardReponse<List<VendorResponseDto>>> getAll() throws Exception {
        try{
            List<VendorResponseDto> vendors = vendorService.findAll();
            return ResponseEntity.ok().body(new StandardReponse<>(HttpStatus.OK.value(), "", vendors));
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<StandardReponse<VendorResponseDto>> createOne(@Valid VendorRequestDto requestDto)
            throws Exception {
        try {

            logger.info("Vendor request: {}", requestDto);

            VendorResponseDto vendor = vendorService.createOne(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new StandardReponse<>(HttpStatus.CREATED.value(), "Vendor has been created successfully!", vendor));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<StandardReponse<VendorResponseDto>> updateOne(int id, @Valid VendorRequestDto requestDto)
            throws Exception {
        try {
            VendorResponseDto vendor = vendorService.updateOne(id, requestDto);
            return ResponseEntity.ok().body(new StandardReponse<>(HttpStatus.OK.value(), "Vendor has been updated successfully!", vendor));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<StandardReponse<VendorResponseDto>> deleteOne(int id) throws Exception {
        try {
            VendorResponseDto deletedVendor = vendorService.deleteOne(id);
            return ResponseEntity.ok().body(new StandardReponse<>(HttpStatus.OK.value(), "Vendor has been deleted successfully!", deletedVendor));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

}
